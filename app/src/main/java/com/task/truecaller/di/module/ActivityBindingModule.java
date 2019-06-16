package com.task.truecaller.di.module;

import com.task.truecaller.ui.activity.MainActivity;
import com.task.truecaller.ui.activity.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Nitish Singh on 2019-06-17.
 */

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {FragmentBindingModule.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();
}
