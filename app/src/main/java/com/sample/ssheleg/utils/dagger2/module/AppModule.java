package com.sample.ssheleg.utils.dagger2.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.sample.ssheleg.SampleApplication;
import com.sample.ssheleg.data.database.HelperFactory;
import com.sample.ssheleg.data.rest.ApiService;
import com.sample.ssheleg.data.rest.factory.RetrofitApiFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by Android Ninja Sergey on 17.11.2016.
 * skype: sergey.sheleg2
 */

@Module
public class AppModule {
    private SampleApplication application;

    public AppModule(@NonNull SampleApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    SampleApplication provideApplication() {
        return application;
    }

    @Provides
    Context provideContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    SharedPreferences providePreference() {
        return application.getSharedPreferences(application.getPackageName(), Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    ApiService provideApiService() {
        return RetrofitApiFactory.createService(ApiService.class);
    }

    @Provides
    @Singleton
    HelperFactory provideDbFactory(SampleApplication application) {
        return new HelperFactory(application);
    }
}
