package com.anikulki.newsapp.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anikulki.newsapp.repository.ArticleRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class NewsArticleViewModel @ViewModelInject constructor(
    private val articleRepository: ArticleRepository,
): ViewModel(){

    fun getTopNews(){
        viewModelScope.launch {
            try {
                val apiResponse = articleRepository.getTopNews()
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

}