package com.task.mercari.di.module;

import com.task.mercari.ui.activity.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Nitish Singh on 2019-06-01.
 */

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {FragmentBindingModule.class})
    abstract MainActivity bindMainActivity();
}
