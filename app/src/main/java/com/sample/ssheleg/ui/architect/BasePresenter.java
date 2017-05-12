package com.sample.ssheleg.ui.architect;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;

/**
 * Created by Android Ninja Sergey on 18.11.2016.
 * skype: sergey.sheleg2
 */

public abstract class BasePresenter<T extends BaseMvpView> {

    private T view;

    public void attachView(T view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
    }

    public T getView() {
        return view;
    }


    public void onCreate() {

    }

    public void onStart() {

    }

    public void onResume() {

    }

    public void onPause() {

    }

    public void onStop() {

    }

    public void onDestroy() {

    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    public void onConfigurationChanged(Configuration newConfig) {

    }
}
