package com.sample.ssheleg.utils.dagger2.module;

import android.content.SharedPreferences;

import com.sample.ssheleg.data.DataManager;
import com.sample.ssheleg.data.database.HelperFactory;
import com.sample.ssheleg.data.rest.ApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Android Ninja Sergey on 17.11.2016.
 * skype: sergey.sheleg2
 */
@Module
public class ManagerModule {

    @Provides
    @Singleton
    DataManager provideDataManager(HelperFactory dbHelper, ApiService apiService, SharedPreferences preferences) {
        return new DataManager(apiService, dbHelper, preferences);
    }
}
