package com.sample.ssheleg;

import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;

import com.sample.ssheleg.data.database.HelperFactory;
import com.sample.ssheleg.utils.dagger2.component.AppComponent;
import com.sample.ssheleg.utils.dagger2.component.DaggerAppComponent;
import com.sample.ssheleg.utils.dagger2.module.AppModule;
import com.sample.ssheleg.utils.dagger2.module.ManagerModule;

import javax.inject.Inject;

/**
 * Created by Android Ninja Sergey on 17.11.2016.
 * skype: sergey.sheleg2
 */

public class SampleApplication extends MultiDexApplication {

    private static AppComponent component;
    private static SampleApplication instance;

    @Inject
    HelperFactory helperFactory;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        component = buildComponent();
        component.inject(this);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public static SampleApplication getInstance() {
        return instance;
    }

    public static AppComponent getComponent() {
        return component;
    }

    private AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .managerModule(new ManagerModule())
                .build();
    }

    @Override
    public void onTerminate() {
        helperFactory.releaseHelper();
        super.onTerminate();
    }

}
