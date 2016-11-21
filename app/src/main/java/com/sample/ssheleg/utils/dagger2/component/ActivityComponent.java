package com.sample.ssheleg.utils.dagger2.component;

import com.sample.ssheleg.ui.activity.WorldMapActivity;
import com.sample.ssheleg.utils.dagger2.module.ActivityModule;
import com.sample.ssheleg.utils.dagger2.scope.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Android Ninja Sergey on 17.11.2016.
 * skype: sergey.sheleg2
 */

@Subcomponent(modules = {ActivityModule.class})
@ActivityScope
public interface ActivityComponent {

    void inject(WorldMapActivity instance);

}
