package com.task.truecaller.viewmodel;

import android.os.Build;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.task.truecaller.repository.RepositoryService;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        Reader reader = new StringReader(body);
        int ch;
        List<String> list = new ArrayList<>();
        int index = 1;
        try {
            while ((ch = reader.read()) != -1) {
                if (!Character.isWhitespace((char) ch)) {
                    if ((++index) % 10 == 0) {
                        list.add(String.valueOf((char) ch));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                //ignore
            }
        }


        StringBuilder stringBuilder = new StringBuilder();
        for (String word : list) {
            stringBuilder.append(word).append("\n");
        }

        everytrueCall10thCharacter.postValue(stringBuilder.toString());


//        String[] charArray = body.split("");
//        List<String> characterList = Arrays.asList(charArray);
//
//
//                StringBuilder stringBuilder = new StringBuilder();
//                for (String word : result) {
//                    stringBuilder.append(word).append("\n");
//                }
//
//        everytrueCall10thCharacter.postValue(stringBuilder.toString());
    }

    private void wordCount(String body) {
        int ch;
        Reader reader = new StringReader(body);
        Map<String, Integer> wordsCount = new HashMap<>();
        StringBuilder word = new StringBuilder();
        try {
            while ((ch = reader.read()) != -1) {
                if (Character.isWhitespace((char) ch)) {
                    if (word.length() > 0) {
                        String wordSmallCase = word.toString().toLowerCase();
                        Integer c = wordsCount.get(wordSmallCase);
                        int count = 0;
                        if (c != null) {
                            count = c;
                        }
                        count++;
                        wordsCount.put(wordSmallCase, count);
                        word.delete(0, word.length());
                    }
                } else {
                    word.append((char) ch);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                //ignore
            }
        }


        StringBuilder stringBuilder = new StringBuilder();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            wordsCount.forEach((key, value) -> {

                stringBuilder.append(key).append(" : ").append(value).append("\n");

            });
        }
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
