package com.sample.ssheleg.utils.dagger2;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sample.ssheleg.SampleApplication;
import com.sample.ssheleg.ui.architect.BaseMvpView;
import com.sample.ssheleg.utils.DialogUtils;
import com.sample.ssheleg.utils.dagger2.component.ActivityComponent;
import com.sample.ssheleg.utils.dagger2.module.ActivityModule;

/**
 * Created by Android Ninja Sergey on 17.11.2016.
 * skype: sergey.sheleg2
 */

public abstract class DaggerActivity extends AppCompatActivity implements BaseMvpView {

    public abstract void injectActivity(ActivityComponent component);

    protected ActivityComponent activityComponent;

    private ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent = SampleApplication.getComponent().plusActivityComponent(new ActivityModule(this));
        injectActivity(activityComponent);
        attachPresenters();

    }

    @Override
    protected void onDestroy() {
        hideProgress();
        destroyPresenters();
        activityComponent = null;
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void showProgress() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(DaggerActivity.this);
        }
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.hide();
        }
    }

    @Override
    public Dialog showDialog(DialogUtils.ID id) {
        Dialog dialog = DialogUtils.create(id, DaggerActivity.this);
        dialog.show();
        return dialog;
    }
}
