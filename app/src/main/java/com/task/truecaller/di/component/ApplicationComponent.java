package com.task.truecaller.di.component;

import android.app.Application;

import com.task.truecaller.application.TrueCallerApplication;
import com.task.truecaller.di.module.ActivityBindingModule;
import com.task.truecaller.di.module.ContextModule;
import com.task.truecaller.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Nitish Singh on 2019-06-17.
 */
@Singleton
@Component(modules = {ContextModule.class, NetworkModule.class, AndroidSupportInjectionModule.class, ActivityBindingModule.class})
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {

    void inject(TrueCallerApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        ApplicationComponent build();
    }
}
