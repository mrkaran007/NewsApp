package com.mrk.newsapp.myViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrk.newsapp.models.NewsModels
import com.mrk.newsapp.repository.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    val response = mutableStateOf<NewsModels?>(null)

    val repo = Repo()

    init {
        fetchNews()
    }

    fun fetchNews(category: String = "business") {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repo.newsProvider(
                country = "us",
                category = category
            )

            response.value = data.body()
        }
    }
}