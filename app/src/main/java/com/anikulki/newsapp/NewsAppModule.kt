package com.anikulki.newsapp

import android.app.Application
import androidx.room.Room
import com.anikulki.newsapp.data.local.db.DatabaseService
import com.anikulki.newsapp.data.remote.NetworkService
import com.anikulki.newsapp.data.remote.Networking
import com.anikulki.newsapp.utils.common.Constants
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NewsAppModule {

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                        else HttpLoggingInterceptor.Level.NONE
                    }
            )
            .readTimeout(Constants.NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(Constants.NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .build()


    @Provides
    @Singleton
    fun provideNetworkService(client: Lazy<OkHttpClient>): NetworkService =
        Networking.create(
            BuildConfig.API_KEY,
            BuildConfig.BASE_URL,
            client
        )


    @Provides
    @Singleton
    fun provideDatabaseService(application: Application): DatabaseService =
        Room.databaseBuilder(
            application, DatabaseService::class.java,
            "news-app-db"
        ).fallbackToDestructiveMigration()
            .build()
}