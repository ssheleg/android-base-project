package com.sample.ssheleg.ui.architect;

import android.app.Dialog;

import com.sample.ssheleg.utils.DialogUtils;

/**
 * Created by Android Ninja Sergey on 17.11.2016.
 * skype: sergey.sheleg2
 */

public interface BaseMvpView {

    public void showProgress();

    public void hideProgress();

    public Dialog showDialog(DialogUtils.ID id);

    public void attachPresenters();

    public void destroyPresenters();

}
