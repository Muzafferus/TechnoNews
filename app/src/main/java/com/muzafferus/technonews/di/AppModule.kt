package com.muzafferus.technonews.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.muzafferus.technonews.data.local.AppDatabase
import com.muzafferus.technonews.data.local.ArticleDao
import com.muzafferus.technonews.data.remote.NewsRemoteDataSource
import com.muzafferus.technonews.data.remote.NewsService
import com.muzafferus.technonews.data.repository.NewsRepository
import com.muzafferus.technonews.util.Utility
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, httpClient: OkHttpClient.Builder): Retrofit = Retrofit.Builder()
        .baseUrl(Utility.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(httpClient.build())
        .build()

    @Provides
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        return httpClient
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideNewsService(retrofit: Retrofit): NewsService =
        retrofit.create(NewsService::class.java)

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(newsService: NewsService) =
        NewsRemoteDataSource(newsService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideArticleDao(db: AppDatabase) = db.articleDao()

    @Singleton
    @Provides
    fun provideNewsRepository(
        localDataSource: ArticleDao,
        remoteDataSource: NewsRemoteDataSource
    ) = NewsRepository(localDataSource, remoteDataSource)
}