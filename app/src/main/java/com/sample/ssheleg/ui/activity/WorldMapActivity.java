package com.sample.ssheleg.ui.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.sample.ssheleg.R;
import com.sample.ssheleg.data.model.map.Capital;
import com.sample.ssheleg.ui.architect.presenter.WorldMapPresenter;
import com.sample.ssheleg.ui.architect.view.WorldMapMvpView;
import com.sample.ssheleg.utils.AppUtils;
import com.sample.ssheleg.utils.bus.UIBus;
import com.sample.ssheleg.utils.bus.message.ConnectionMessage;
import com.sample.ssheleg.utils.dagger2.DaggerActivity;
import com.sample.ssheleg.utils.dagger2.component.ActivityComponent;
import com.squareup.otto.Subscribe;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Android Ninja Sergey on 17.11.2016.
 * skype: sergey.sheleg2
 */
public class WorldMapActivity extends DaggerActivity implements WorldMapMvpView, OnMapReadyCallback {

    @Inject
    WorldMapPresenter presenter;

    @Inject
    UIBus uiBus;

    @BindView(R.id.container)
    FrameLayout container;

    private GoogleMap mMap;

    @Override
    public void injectActivity(ActivityComponent component) {
        component.inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_empty_frame);
        ButterKnife.bind(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentByTag(SupportMapFragment.class.getSimpleName());
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance(new GoogleMapOptions()
                    .mapType(GoogleMap.MAP_TYPE_NORMAL));
            mapFragment.getMapAsync(this);
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(container.getId(), mapFragment, SupportMapFragment.class.getSimpleName())
                .commit();
        uiBus.post(new ConnectionMessage(AppUtils.isNetworkAvailable(this)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        uiBus.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        uiBus.unregister(this);
    }

    @Subscribe
    public void getConnectionMessage(ConnectionMessage data) {
        Toast.makeText(this, data.enabled ? "Connection enabled" : "Connection lost", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        presenter.updateCapitals();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void addCapitalsMarkers(List<Capital> capitals) {
        for (Capital capital : capitals) {
            mMap.addMarker(capital.buildMarker());
            if (capital.code.equalsIgnoreCase(Locale.getDefault().getCountry())) {
                mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(capital.latitude, capital.longitude)));
            }
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
