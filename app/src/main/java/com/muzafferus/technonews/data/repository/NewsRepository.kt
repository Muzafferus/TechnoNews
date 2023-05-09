package com.muzafferus.technonews.data.repository

import com.muzafferus.technonews.data.entities.Article
import com.muzafferus.technonews.data.local.ArticleDao
import com.muzafferus.technonews.data.remote.NewsRemoteDataSource
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val localDataSource: ArticleDao,
    private val remoteDataSource: NewsRemoteDataSource
) {
    suspend fun getNews(category: String) = remoteDataSource.getArticleList(category)

    fun getArticles() = localDataSource.getArticles()

    suspend fun setArticles(article: Article) {
        localDataSource.insert(article)
    }

    suspend fun deleteAll() {
        localDataSource.deleteAll()
    }

    fun getArticle(id: String) = localDataSource.getArticle(id.toInt())
}