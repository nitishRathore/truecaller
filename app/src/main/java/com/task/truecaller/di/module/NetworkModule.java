package com.task.truecaller.di.module;

import com.task.truecaller.repository.network.NetworkService;
import com.task.truecaller.util.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nitish Singh on 2019-06-17.
 */

@Module(includes = ViewModelModule.class)
public class NetworkModule {



    @Singleton
    @Provides
    static OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
        return new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
    }

    @Singleton
    @Provides
    static Retrofit provideRetrofit(OkHttpClient okHttpClient) {

        return new Retrofit.Builder().baseUrl(AppConstants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    @Singleton
    @Provides
    static NetworkService provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(NetworkService.class);
    }
}
