package com.sample.ssheleg.utils.bus;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Android Ninja Sergey on 17.11.2016.
 * skype: sergey.sheleg2
 */

@Singleton
public class UIBus extends Bus {

    private final Handler mHandler = new Handler(Looper.getMainLooper());

    @Inject
    public UIBus() {
    }

    @Override
    public void post(final Object event) {
        if (event != null) {
            mHandler.post(() -> UIBus.super.post(event));
        }
    }
}