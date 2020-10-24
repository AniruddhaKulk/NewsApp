package com.anikulki.newsapp.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anikulki.newsapp.data.local.db.entity.ArticleEntity
import com.anikulki.newsapp.repository.ArticleRepository
import com.anikulki.newsapp.utils.common.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class NewsArticleViewModel @ViewModelInject constructor(
    private val articleRepository: ArticleRepository,
): ViewModel(){

    private val _articlesLiveData = MutableLiveData<State<List<ArticleEntity>>>()

    val articlesLiveData: LiveData<State<List<ArticleEntity>>>
            get() = _articlesLiveData


    fun getTopNews(){
        viewModelScope.launch {
            articleRepository.getTopNews().collect {
                _articlesLiveData.value = it
            }
        }
    }

}