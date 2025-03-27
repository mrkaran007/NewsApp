package com.mrk.newsapp.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mrk.newsapp.myViewModel.MyViewModel
import com.mrk.newsapp.screens.NewsContentScreen
import com.mrk.newsapp.screens.NewsScreen

@Composable
fun MyAppNavigation(viewModel: MyViewModel) {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.newsScreen){
        composable(Routes.newsScreen) {
            NewsScreen(navController, viewModel)
        }

        composable(Routes.newsContentScreen + "/{url}") {
            val url = it.arguments?.getString("url")
            NewsContentScreen(navController, url!!)
        }
    }
}