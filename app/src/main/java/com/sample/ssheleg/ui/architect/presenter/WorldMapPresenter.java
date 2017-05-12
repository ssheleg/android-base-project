package com.sample.ssheleg.ui.architect.presenter;

import com.sample.ssheleg.data.DataManager;
import com.sample.ssheleg.ui.architect.BaseMvpView;
import com.sample.ssheleg.ui.architect.BasePresenter;
import com.sample.ssheleg.ui.architect.view.WorldMapMvpView;

import java.util.Arrays;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Android Ninja Sergey on 18.11.2016.
 * skype: sergey.sheleg2
 */

public class WorldMapPresenter extends BasePresenter<WorldMapMvpView> {

    private DataManager dataManager;
    private CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    public WorldMapPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void detachView() {
        if (disposables != null) {
            disposables.clear();
        }
        super.detachView();
    }


    public void updateCapitals() {
        getView().showProgress();
        disposables.add(Observable.combineLatest(
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
                .subscribe(
                        capitals -> {//onNext
                            getView().addCapitalsMarkers(capitals);
                            getView().hideProgress();
                        },
                        throwable -> {//onError
                            getView().hideProgress();
                        },
                        () -> {//onComplete
                        },
                        disposable -> {//onSubscribe
                        }
                )
        );
    }

}