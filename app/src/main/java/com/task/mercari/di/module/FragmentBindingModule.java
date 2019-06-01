package com.task.mercari.di.module;

import com.task.mercari.ui.fragment.AllProductsFragment;
import com.task.mercari.ui.fragment.MenProductsFragment;
import com.task.mercari.ui.fragment.WomenProductsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Nitish Singh on 2019-06-01.
 */

@Module
public abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract MenProductsFragment bindMenProductsFragment();

    @ContributesAndroidInjector
    abstract WomenProductsFragment bindWomenProductsFragment();

    @ContributesAndroidInjector
    abstract AllProductsFragment bindAllProductsFragment();


}
