package com.sample.ssheleg.data;

import android.content.SharedPreferences;

import com.sample.ssheleg.data.database.HelperFactory;
import com.sample.ssheleg.data.model.map.Capital;
import com.sample.ssheleg.data.rest.ApiService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by Android Ninja Sergey on 17.11.2016.
 * skype: sergey.sheleg2
 */
@Singleton
public class DataManager {
    private ApiService apiService;
    private HelperFactory databaseManager;
    private SharedPreferences preferences;

    @Inject
    public DataManager(ApiService apiService, HelperFactory databaseManager, SharedPreferences preferences) {
        this.apiService = apiService;
        this.databaseManager = databaseManager;
        this.preferences = preferences;
    }

    public Observable<List<Capital>> getCapitalsRest() {
        return apiService.getCapitals();
    }

    public Observable<List<Capital>> getCapitalsDatabase() {
        return Observable.just(databaseManager.getHelper().getCapitalDAO().getAll());
    }

    public void saveCapitals(List<Capital> capitals) {
        databaseManager.getHelper().getCapitalDAO().replace(capitals);
    }

}
