package com.mrk.newsapp.repository

import com.mrk.newsapp.models.NewsModels
import com.mrk.newsapp.network.ApiProvider
import retrofit2.Response

//  Repository helps in to differentiate  b/w data is fetch from API or local
class Repo {
    suspend fun newsProvider(country: String, category: String): Response<NewsModels> {
        return ApiProvider.provideApi().getTopHeadlines(country = country, category = category)
    }
}