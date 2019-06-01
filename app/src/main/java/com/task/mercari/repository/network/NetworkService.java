package com.task.mercari.repository.network;

import com.task.mercari.model.Product;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by Nitish Singh on 2019-06-01.
 */
public interface NetworkService {

    @GET("men.json")
    Single<List<Product>> getMenProducts();

    @GET("all.json")
    Single<List<Product>> getAllProducts();

    @GET("women.json")
    Single<List<Product>> getWomenProducts();
}
