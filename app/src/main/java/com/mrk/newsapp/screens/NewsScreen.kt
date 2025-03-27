package com.mrk.newsapp.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.mrk.newsapp.myViewModel.MyViewModel
import com.mrk.newsapp.navigation.Routes
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(navController: NavController, viewModel: MyViewModel) {
    val response = viewModel.response.value?.articles ?: emptyList()

    Scaffold(

        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(text = "NewsShots", fontWeight = FontWeight.Bold)
                },
            )
        }

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(color = Color.LightGray)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.DarkGray)
                ) {
                    Text(
                        text = "Categories:- ",
                        modifier = Modifier
                            .background(color = Color.White)
                            .padding(6.dp),
                        fontWeight = FontWeight.SemiBold
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(28.dp)
                            .padding(2.dp)
                            .horizontalScroll(rememberScrollState()),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        CategoriesCard("business", viewModel)
                        CategoriesCard("entertainment", viewModel)
                        CategoriesCard("health", viewModel)
                        CategoriesCard("sports", viewModel)
                        CategoriesCard("science", viewModel)

                    }

                }
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                items(response) {
                    if (it.title != null && it.content != null && it.urlToImage != null && it.description != null) {
                        val encodedUrl =
                            URLEncoder.encode(it.url, StandardCharsets.UTF_8.toString())
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 8.dp)
                                .clickable(
                                    onClick = {
                                        navController.navigate("${Routes.newsContentScreen}/$encodedUrl")
                                    }
                                )
                        ) {


                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(color = Color.White)
                                    .padding(6.dp)
                            ) {

                                AsyncImage(
                                    model = it.urlToImage,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp)
                                        .clip(shape = RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop

                                )

                                Text(
                                    text = it.title,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 22.sp,
                                    textAlign = TextAlign.Justify
                                )
                                Text(text = it.description, maxLines = 3)
//                                it.description?.let { text -> Text(text = text, maxLines = 2) }
                                Log.d("TAG", "NewsScreen: ${it.description}")
                            }


                        }
                    }

                }
            }
        }
    }
}

@Composable
fun CategoriesCard(category: String, viewModel: MyViewModel) {
    Card(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(6.dp))
            .background(Color.White)
    ) {
        Text(
            text = category,
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 4.dp)
                .clickable(
                    onClick = {
                        viewModel.fetchNews(category)

                    }
                )
        )
    }
}