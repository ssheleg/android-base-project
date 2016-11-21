package com.sample.ssheleg.ui.architect.view;

import com.sample.ssheleg.data.model.map.Capital;
import com.sample.ssheleg.ui.architect.BaseMvpView;

import java.util.List;


/**
 * Created by Android Ninja Sergey on 18.11.2016.
 * skype: sergey.sheleg2
 */

public interface WorldMapMvpView extends BaseMvpView {

    public void addCapitalsMarkers(List<Capital> capitals);

}
