package com.sample.ssheleg.utils.dagger2.component;

import com.sample.ssheleg.utils.dagger2.module.FragmentModule;
import com.sample.ssheleg.utils.dagger2.scope.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Android Ninja Sergey on 17.11.2016.
 * skype: sergey.sheleg2
 */

@Subcomponent(modules = {FragmentModule.class})
@FragmentScope
public interface FragmentComponent {

//    void inject(Fragment instance);

}

