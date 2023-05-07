package com.muzafferus.technonews.data.remote

import com.muzafferus.technonews.util.Utility
import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(
    private val newsService: NewsService
) : BaseDataSource() {

    suspend fun getArticleList() = getResult { newsService.getArticleList(Utility.API_KEY) }
}