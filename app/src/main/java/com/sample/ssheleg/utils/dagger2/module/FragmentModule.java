package com.sample.ssheleg.utils.dagger2.module;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.sample.ssheleg.utils.dagger2.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Android Ninja Sergey on 17.11.2016.
 * skype: sergey.sheleg2
 */
@Module
public class FragmentModule {
    private Fragment fragment;

    public FragmentModule(@NonNull Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    Fragment provideFragment() {
        return fragment;
    }

    @Provides
    @FragmentScope
    Activity provideActivity() {
        return fragment.getActivity();
    }

    @Provides
    @FragmentScope
    Context provideContext() {
        return fragment.getContext();
    }
}
