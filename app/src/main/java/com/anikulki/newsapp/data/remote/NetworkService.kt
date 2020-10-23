package com.anikulki.newsapp.data.remote

import com.anikulki.newsapp.BuildConfig
import com.anikulki.newsapp.data.remote.response.ArticlesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET(EndPoints.TOP_NEWS)
    suspend fun callTopNews(@Query("apikey") apiKey: String = BuildConfig.API_KEY
        ): Response<ArticlesResponse>

}