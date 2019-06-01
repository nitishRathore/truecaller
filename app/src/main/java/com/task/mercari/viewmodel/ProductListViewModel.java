package com.task.mercari.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.task.mercari.model.Product;
import com.task.mercari.repository.RepositoryService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Nitish Singh on 2019-06-01.
 */
public class ProductListViewModel extends ViewModel {

    private final RepositoryService repoRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<List<Product>> mensProducts = new MutableLiveData<>();
    private final MutableLiveData<List<Product>> womensProducts = new MutableLiveData<>();
    private final MutableLiveData<List<Product>> allProducts = new MutableLiveData<>();
    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();


    @Inject
    public ProductListViewModel(RepositoryService repoRepository) {
        this.repoRepository = repoRepository;
        disposable = new CompositeDisposable();
        fetchMensProducts();
        fetchWomensProducts();
        fetchAllProducts();
    }




    public LiveData<List<Product>> getMensProduct() {
        return mensProducts;
    }
    public LiveData<List<Product>> getWomensProduct() {
        return womensProducts;
    }
    public LiveData<List<Product>> getAllProduct() {
        return allProducts;
    }

    public LiveData<Boolean> getError() {
        return repoLoadError;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }


    private void fetchMensProducts() {
        loading.setValue(true);
        disposable.add(repoRepository.getMensProductsRepositories().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<List<Product>>() {
                    @Override
                    public void onSuccess(List<Product> value) {
                        repoLoadError.setValue(false);
                        mensProducts.setValue(value);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        repoLoadError.setValue(true);
                        loading.setValue(false);
                    }
                }));
    }


    private void fetchAllProducts() {
        loading.setValue(true);
        disposable.add(repoRepository.getAllProductsRepositories().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<List<Product>>() {
                    @Override
                    public void onSuccess(List<Product> value) {
                        repoLoadError.setValue(false);
                        allProducts.setValue(value);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        repoLoadError.setValue(true);
                        loading.setValue(false);
                    }
                }));
    }

    private void fetchWomensProducts() {
        loading.setValue(true);
        disposable.add(repoRepository.getWomensProductsRepositories().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<List<Product>>() {
                    @Override
                    public void onSuccess(List<Product> value) {
                        repoLoadError.setValue(false);
                        womensProducts.setValue(value);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        repoLoadError.setValue(true);
                        loading.setValue(false);
                    }
                }));
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
