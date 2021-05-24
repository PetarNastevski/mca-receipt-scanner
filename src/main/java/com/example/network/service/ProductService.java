package com.example.network.service;

import com.example.network.response.Product;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface ProductService {

    @GET("qr-scanner-codes/alpha-qr-gFpwhsQ8fkY1")
    Call<List<Product>> getProducts();
}
