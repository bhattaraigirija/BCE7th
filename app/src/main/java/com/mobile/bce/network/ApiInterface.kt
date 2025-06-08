package com.mobile.bce.network
import com.mobile.bce.model.Product
import retrofit2.Call
import retrofit2.http.*
interface ApiInterface {

    @GET("products")
    fun getProducts(): Call<List<Product>>

    @POST("products")
    fun postProduct(@Body product: Product): Call<Product>
}