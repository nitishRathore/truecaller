package com.task.mercari.di.component;

import android.app.Application;

import com.task.mercari.base.BaseApplication;
import com.task.mercari.di.module.ActivityBindingModule;
import com.task.mercari.di.module.ContextModule;
import com.task.mercari.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Nitish Singh on 2019-06-01.
 */
@Singleton
@Component(modules = {ContextModule.class, NetworkModule.class, AndroidSupportInjectionModule.class, ActivityBindingModule.class})
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {

    void inject(BaseApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        ApplicationComponent build();
    }
}
