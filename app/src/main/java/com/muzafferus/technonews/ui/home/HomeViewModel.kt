package com.muzafferus.technonews.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muzafferus.technonews.data.entities.Article
import com.muzafferus.technonews.data.repository.NewsRepository
import com.muzafferus.technonews.util.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _newsList by lazy {
        MutableLiveData<ViewState<
                ArrayList<Article>>>()
    }
    val newsList: LiveData<ViewState<
            ArrayList<Article>>>
        get() = _newsList

    fun getArticleList() = viewModelScope.launch {
        _newsList.postValue(ViewState.Loading())
        try {
            newsRepository.getArticles().collect { dbList ->
                if (dbList.isEmpty()) {
                    val response = newsRepository.getNews()
                    response.data?.articles?.map { article ->
                        newsRepository.setArticles(article)
                    }

                    newsRepository.getArticles().collect { list ->
                        _newsList.postValue(ViewState.Success(ArrayList(list)))
                    }
                } else {
                    _newsList.postValue(ViewState.Success(ArrayList(dbList)))
                }
            }
        } catch (e: Exception) {
            _newsList.postValue(ViewState.Error(e.message))
        }
    }

    fun deleteAll() {
        newsRepository.deleteAll()
    }
}