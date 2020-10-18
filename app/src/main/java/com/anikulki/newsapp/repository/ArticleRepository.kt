package com.anikulki.newsapp.repository

import com.anikulki.newsapp.data.remote.NetworkService
import dagger.Lazy
import javax.inject.Inject

class ArticleRepository @Inject constructor(
    private val networkService: Lazy<NetworkService>
){
    suspend fun getTopNews(): String{
        return networkService.get().callTopNews()
    }
}