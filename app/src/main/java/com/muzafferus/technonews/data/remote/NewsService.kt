package com.muzafferus.technonews.data.remote

import com.muzafferus.technonews.data.entities.NewsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("v2/top-headlines")
    suspend fun getArticleList(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String,
        @Query("category") category: String
    ): Response<NewsModel>
}