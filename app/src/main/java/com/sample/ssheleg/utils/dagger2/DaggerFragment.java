package com.sample.ssheleg.utils.dagger2;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.sample.ssheleg.SampleApplication;
import com.sample.ssheleg.utils.dagger2.component.FragmentComponent;
import com.sample.ssheleg.utils.dagger2.module.FragmentModule;

/**
 * Created by Android Ninja Sergey on 17.11.2016.
 * skype: sergey.sheleg2
 */

public abstract class DaggerFragment extends Fragment {
    protected FragmentComponent component;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component = SampleApplication.getComponent().plusFragmentComponent(new FragmentModule(this));
        injectFragment(component);
    }

    @Override
    public void onDestroy() {
        component = null;
        super.onDestroy();
    }

    public abstract void injectFragment(FragmentComponent fragmentComponent);
}
