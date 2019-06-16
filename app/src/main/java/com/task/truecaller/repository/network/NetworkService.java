package com.task.truecaller.repository.network;

import com.task.truecaller.util.AppConstants;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * Created by Nitish Singh on 2019-06-17.
 */
public interface NetworkService {


    @GET(AppConstants.LIFE_ANDROID_ENG)
    Observable<ResponseBody> getTruecaller10thCharacterRequest();

    @GET(AppConstants.LIFE_ANDROID_ENG)
    Observable<ResponseBody> getTruecallerEvery10thCharacterRequest();


    @GET(AppConstants.LIFE_ANDROID_ENG)
    Observable<ResponseBody> getTruecallerWordCounterRequest();
}
