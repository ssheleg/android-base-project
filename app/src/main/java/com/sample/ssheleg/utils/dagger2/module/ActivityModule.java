package com.sample.ssheleg.utils.dagger2.module;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.sample.ssheleg.utils.dagger2.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Android Ninja Sergey on 17.11.2016.
 * skype: sergey.sheleg2
 */

@Module
public class ActivityModule {
    public Activity activity;

    public ActivityModule(@NonNull Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    Activity provideActivity() {
        return activity;
    }

    @Provides
    @ActivityScope
    Context provideContext() {
        return activity;
    }

}
