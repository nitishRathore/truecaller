package com.task.truecaller.repository;

import com.task.truecaller.repository.network.NetworkService;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Nitish Singh on 2019-06-17
 */
public class RepositoryService {

    private final NetworkService networkService;

    @Inject
    public RepositoryService(NetworkService networkService) {
        this.networkService = networkService;
    }



    public Observable<ResponseBody> get10thCharacterRequest() {
        return networkService.getTruecaller10thCharacterRequest();
    }

    public Observable<ResponseBody> getEvery10thCharacterRequest() {
        return networkService.getTruecallerEvery10thCharacterRequest();
    }

    public Observable<ResponseBody> getWordCounterRequest() {
        return networkService.getTruecallerWordCounterRequest();
    }
}
