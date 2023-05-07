package com.muzafferus.technonews.data.local

import androidx.room.*
import com.muzafferus.technonews.data.entities.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles")
    fun getArticles(): Flow<List<Article>>

    @Query("SELECT * FROM articles WHERE id = :id")
    fun getArticle(id: Int): Flow<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Delete
    suspend fun delete(article: Article)
}