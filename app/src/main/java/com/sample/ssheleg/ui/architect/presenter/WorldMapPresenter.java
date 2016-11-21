package com.sample.ssheleg.ui.architect.presenter;

import android.util.Log;

import com.sample.ssheleg.data.DataManager;
import com.sample.ssheleg.data.model.map.Capital;
import com.sample.ssheleg.ui.architect.BasePresenter;
import com.sample.ssheleg.ui.architect.view.WorldMapMvpView;
import com.sample.ssheleg.utils.DialogUtils;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Android Ninja Sergey on 18.11.2016.
 * skype: sergey.sheleg2
 */

public class WorldMapPresenter extends BasePresenter {

    private DataManager dataManager;
    private CompositeSubscription subscription = new CompositeSubscription();

    @Inject
    public WorldMapPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void detachView() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
        super.detachView();
    }

    public void updateCapitals() {
        getMvpView().showProgress();
        subscription.add(Observable.combineLatest(
                dataManager.getCapitalsRest(),
                dataManager.getCapitalsDatabase(),
                (restList, dbList) -> {
                    if (restList == null || restList.isEmpty()) {
                        return dbList;
                    } else if (dbList == null || dbList.isEmpty()) {
                        dataManager.saveCapitals(restList);
                        return restList;
                    } else {
                        if (Arrays.deepEquals(dbList.toArray(), restList.toArray())) {
                            return dbList;
                        } else {
                            dataManager.saveCapitals(restList);
                            return restList;
                        }
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Capital>>() {
                    @Override
                    public void onCompleted() {
                        Log.i("API", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("API", "onError");
                        e.printStackTrace();
                        getMvpView().showDialog(DialogUtils.ID.ERROR_SOME_HAPPENED);
                        getMvpView().hideProgress();
                    }

                    @Override
                    public void onNext(List<Capital> capitals) {
                        Log.i("API", "onNext " + capitals.toString());
                        ((WorldMapMvpView) getMvpView()).addCapitalsMarkers(capitals);
                        getMvpView().hideProgress();
                    }
                }));
    }

}
