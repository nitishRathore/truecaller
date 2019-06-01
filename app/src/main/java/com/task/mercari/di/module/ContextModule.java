package com.task.mercari.di.module;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Nitish Singh on 2019-06-01.
 */

@Module
public abstract class ContextModule {
    @Binds
    abstract Context provideContext(Application application);
}
