package com.task.mercari.repository;

import com.task.mercari.model.Product;
import com.task.mercari.model.ProductData;
import com.task.mercari.repository.network.NetworkService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by Nitish Singh on 2019-06-01.
 */
public class RepositoryService {

    private final NetworkService networkService;

    @Inject
    public RepositoryService(NetworkService networkService) {
        this.networkService = networkService;
    }


    public Single<List<ProductData>> getProductDataList() {
        return networkService.getProductDataList();
    }

    public Single<List<Product>> getMensProductsRepositories(String mensProductUrl) {
        return networkService.getMenProducts(mensProductUrl);
    }

    public Single<List<Product>> getWomensProductsRepositories(String womensProductUrl) {
        return networkService.getWomenProducts(womensProductUrl);
    }

    public Single<List<Product>> getAllProductsRepositories(String allProductUrl) {
        return networkService.getAllProducts(allProductUrl);
    }
}
