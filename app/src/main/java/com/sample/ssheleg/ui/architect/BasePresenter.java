package com.sample.ssheleg.ui.architect;

/**
 * Created by Android Ninja Sergey on 18.11.2016.
 * skype: sergey.sheleg2
 */

public abstract class BasePresenter<T extends BaseMvpView> {

    private T mvpView;

    public T getMvpView() {
        return mvpView;
    }

    public void attachView(T mvpView) {
        this.mvpView = mvpView;
    }

    public void detachView() {
        mvpView = null;
    }
}
