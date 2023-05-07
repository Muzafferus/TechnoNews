package com.muzafferus.technonews.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

data class NewsModel(
    val articles: ArrayList<Article>,
    val status: String,
    val totalResults: Int
)

@Entity(tableName = "articles")
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val author: String? = "",
    val content: String? = "",
    val description: String? = "",
    val publishedAt: String,
    //val source: Source,
    val title: String? = "",
    val url: String? = "",
    val urlToImage: String? = ""
)

data class Source(
    val id: String,
    val name: String
)