package com.mrk.newsapp.network

import com.mrk.newsapp.utils.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// RetrofitInstance
object ApiProvider {    // It is the instance of Retrofit

    val okHttpClient = OkHttpClient.Builder()
    fun provideApi() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient.build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)!!
}