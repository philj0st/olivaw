package com.example.strapistarter


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface StrapiService {
    @GET("products")
    fun getProducts(): Call<Product>

    @GET("product/{id}")
    fun getProduct(@Path("id") id: Int): Call<Product>

}