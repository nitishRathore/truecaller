package com.task.truecaller.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.task.truecaller.repository.RepositoryService;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function3;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;


public class TrueCallerViewModel extends ViewModel {

    private final RepositoryService repoRepository;
    private CompositeDisposable disposable;


    private final MutableLiveData<Boolean> progressBar = new MutableLiveData<>();
    private final MutableLiveData<String> trueCall10thCharacter = new MutableLiveData<>();
    private final MutableLiveData<String> everytrueCall10thCharacter = new MutableLiveData<>();
    private final MutableLiveData<String> wordCountsMaps = new MutableLiveData<>();
    private Observable<ResponseBody> tenthCharacterObservable, everyTenthCharacterObservable, wordCountObservable;
    private String TAG = TrueCallerViewModel.class.getSimpleName();


    public LiveData<Boolean> getProgress() {
        return progressBar;
    }

    public LiveData<String> getTenthCharacterData() {

        return trueCall10thCharacter;
    }


    public void fetchData() {
        progressBar.postValue(true);

        fetchTrueCallerData();
    }


    public LiveData<String> getEveryTenthCharacterData() {

        return everytrueCall10thCharacter;
    }

    public LiveData<String> getWordsCountData() {
        return wordCountsMaps;
    }


    @Inject
    public TrueCallerViewModel(RepositoryService repoRepository) {
        this.repoRepository = repoRepository;
        this.disposable = new CompositeDisposable();
        tenthCharacterObservable = repoRepository.get10thCharacterRequest().subscribeOn(Schedulers.io());
        everyTenthCharacterObservable = repoRepository.getEvery10thCharacterRequest().subscribeOn(Schedulers.io());
        wordCountObservable = repoRepository.getWordCounterRequest().subscribeOn(Schedulers.io());

    }


    private Function3<ResponseBody, ResponseBody, ResponseBody, ResponseBody> mergeResponseData() {
        return (responseBody, responseBody2, responseBody3) -> {
            find10thCharacter(responseBody.string());
            wordCount(responseBody2.string());
            findEveryTenthCharacter(responseBody3.string());
            return responseBody;
        };

    }


    private void fetchTrueCallerData() {
        trueCall10thCharacter.postValue(null);
        everytrueCall10thCharacter.postValue(null);
        wordCountsMaps.postValue(null);

        disposable.add(Observable.zip(tenthCharacterObservable, everyTenthCharacterObservable, wordCountObservable, mergeResponseData()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<ResponseBody>() {
            @Override
            public void onNext(ResponseBody responseBody) {
            }

            @Override
            public void onError(Throwable e) {
                progressBar.postValue(false);
            }

            @Override
            public void onComplete() {
                progressBar.postValue(false);
            }
        }));


    }

    private void find10thCharacter(String body) {


        if (body != null && body.length() > 0) {
            String[] charArray = body.split("");
            trueCall10thCharacter.postValue(charArray[9]);
        }

    }


    private void findEveryTenthCharacter(String body) {

        String[] charArray = body.split("");
        List<String> characterList = Arrays.asList(charArray);

//        AtomicInteger skip = new AtomicInteger(10);
        int skip = 9;
//        AtomicInteger size = new AtomicInteger(characterList.size());
        int size = characterList.size();
        AtomicInteger limit = new AtomicInteger((size / skip) + Math.min(size % skip, 1));

        List<String> result = Stream.iterate(characterList, l -> l.subList(skip, l.size()))
                .limit(limit.get())
                .map(l -> l.get(0))
                .collect(Collectors.toList());

                StringBuilder stringBuilder = new StringBuilder();
                for (String word : result) {
                    stringBuilder.append(word).append("\n");
                }

        everytrueCall10thCharacter.postValue(stringBuilder.toString());
    }

    private void wordCount(String body) {
        String[] splitArray = body.split(" ");
        if (splitArray.length <= 0) {
            return;
        }
        ConcurrentHashMap<String, Integer> wordsCount = new ConcurrentHashMap<>();
        for (String word : splitArray) {
            Integer count = wordsCount.get(word.trim());
            if (count != null) {

                count++;
            } else {
                count = 1;
            }
            wordsCount.put(word.trim(), count);
        }
        StringBuilder stringBuilder = new StringBuilder();
        wordsCount.forEach((key, value) -> {

            stringBuilder.append(key).append(" : ").append(value).append("\n");

        });
        wordCountsMaps.postValue(stringBuilder.toString());

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
