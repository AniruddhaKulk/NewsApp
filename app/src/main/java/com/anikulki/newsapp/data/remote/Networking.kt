package com.anikulki.newsapp.data.remote

import dagger.Lazy
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Networking {

    internal lateinit var API_KEY: String

    fun create(apiKey: String, baseUrl: String, okHttpClient: Lazy<OkHttpClient>): NetworkService {

        API_KEY = apiKey

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .callFactory { okHttpClient.get().newCall(it) }
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkService::class.java)
    }
}