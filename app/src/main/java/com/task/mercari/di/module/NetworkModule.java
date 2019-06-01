package com.task.mercari.di.module;

import com.task.mercari.repository.network.NetworkService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nitish Singh on 2019-06-01.
 */

@Module(includes = ViewModelModule.class)
public class NetworkModule {

    private static final String BASE_URL = "https://s3-ap-northeast-1.amazonaws.com/m-et/Android/json/";


    @Singleton
    @Provides
    static OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
        return new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();


    }


    @Singleton
    @Provides
    static Retrofit provideRetrofit() {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    @Singleton
    @Provides
    static NetworkService provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(NetworkService.class);
    }
}
