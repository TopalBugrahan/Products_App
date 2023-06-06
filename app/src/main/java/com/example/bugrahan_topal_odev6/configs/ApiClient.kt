package com.example.bugrahan_topal_odev6.configs

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
     private val Base_Url="https://dummyjson.com/"
     private var retrofit: Retrofit?=null

    fun getClient():Retrofit{
        if(retrofit==null){
            retrofit=Retrofit.Builder().baseUrl(Base_Url).addConverterFactory(GsonConverterFactory.create()).build()
        }
        return  retrofit as Retrofit
    }
}