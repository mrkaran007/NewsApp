package com.mrk.newsapp.network

import com.mrk.newsapp.utils.API_KEY
import com.mrk.newsapp.models.NewsModels
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

//    https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=34f966df7afe4b7595d089817e2c3b8f

    @GET("top-headlines")
    suspend fun getTopHeadlines(  //    this function if form for API call
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String = API_KEY
    ) : Response<NewsModels>  // wrap with retrofit response (Return Type)
}