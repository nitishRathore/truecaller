package com.task.mercari.repository.network;

import com.task.mercari.model.Product;
import com.task.mercari.model.ProductData;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Nitish Singh on 2019-06-01.
 */
public interface NetworkService {


    @GET("master.json")
    Single<List<ProductData>> getProductDataList();

    @GET
    Single<List<Product>> getMenProducts(@Url String url);

    @GET
    Single<List<Product>> getAllProducts(@Url String url);

    @GET
    Single<List<Product>> getWomenProducts(@Url String url);
}
