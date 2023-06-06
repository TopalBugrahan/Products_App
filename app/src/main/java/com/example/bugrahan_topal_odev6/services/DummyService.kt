package com.example.bugrahan_topal_odev6.services

import com.example.bugrahan_topal_odev6.models.TenProducts
import com.example.bugrahan_topal_odev6.models.User
import com.example.bugrahan_topal_odev6.models.UserData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DummyService {

    @POST("/auth/login")
    fun login(@Body user:User):Call<UserData>

    @GET("products?limit=10")
    fun getTenProduct():Call<TenProducts>

    @GET("products/search")
    fun searchProducts(@Query("q") query: String):Call<TenProducts>
}