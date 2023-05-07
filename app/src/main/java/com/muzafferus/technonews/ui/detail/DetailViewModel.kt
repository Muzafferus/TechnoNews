package com.muzafferus.technonews.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.muzafferus.technonews.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    fun getArticle(id: String) = liveData {
        newsRepository.getArticle(id).collect {
            emit(it)
        }
    }
}