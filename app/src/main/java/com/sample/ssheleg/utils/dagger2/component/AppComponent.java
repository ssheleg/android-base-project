package com.sample.ssheleg.utils.dagger2.component;

import com.sample.ssheleg.SampleApplication;
import com.sample.ssheleg.utils.dagger2.module.ActivityModule;
import com.sample.ssheleg.utils.dagger2.module.AppModule;
import com.sample.ssheleg.utils.dagger2.module.FragmentModule;
import com.sample.ssheleg.utils.dagger2.module.ManagerModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Android Ninja Sergey on 17.11.2016.
 * skype: sergey.sheleg2
 */

@Component(modules = {AppModule.class, ManagerModule.class})
@Singleton
public interface AppComponent {

    ActivityComponent plusActivityComponent(ActivityModule activityModule);

    FragmentComponent plusFragmentComponent(FragmentModule fragmentModule);

    void inject(SampleApplication application);

}
