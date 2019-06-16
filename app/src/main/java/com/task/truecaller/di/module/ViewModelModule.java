package com.task.truecaller.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.task.truecaller.di.util.ViewModelKey;
import com.task.truecaller.util.ViewModelFactory;
import com.task.truecaller.viewmodel.TrueCallerViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by Nitish Singh on 2019-06-17.
 */

@Module
public abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(TrueCallerViewModel.class)
    abstract ViewModel bindProductListViewModel(TrueCallerViewModel trueCallerViewModel);


    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
