package com.anikulki.newsapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET(EndPoints.TOP_NEWS)
    suspend fun callTopNews(@Query("apikey") apiKey: String = Networking.API_KEY
        ): String

}