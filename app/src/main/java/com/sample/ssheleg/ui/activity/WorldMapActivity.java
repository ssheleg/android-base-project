package com.sample.ssheleg.ui.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.sample.ssheleg.R;
import com.sample.ssheleg.data.model.map.Capital;
import com.sample.ssheleg.ui.architect.presenter.WorldMapPresenter;
import com.sample.ssheleg.ui.architect.view.WorldMapMvpView;
import com.sample.ssheleg.utils.dagger2.DaggerActivity;
import com.sample.ssheleg.utils.dagger2.component.ActivityComponent;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Android Ninja Sergey on 17.11.2016.
 * skype: sergey.sheleg2
 */
public class WorldMapActivity extends DaggerActivity implements WorldMapMvpView {

    @Inject
    WorldMapPresenter presenter;

    @BindView(R.id.container)
    FrameLayout container;


    @Override
    public void injectActivity(ActivityComponent component) {
        component.inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_empty_frame);
        ButterKnife.bind(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void addCapitalsMarkers(List<Capital> capitals) {
        for (Capital capital : capitals) {
        }
    }

    @Override
    public void attachPresenters() {
        presenter.attachView(WorldMapActivity.this);
    }

    @Override
    public void destroyPresenters() {
        presenter.detachView();
    }
}
