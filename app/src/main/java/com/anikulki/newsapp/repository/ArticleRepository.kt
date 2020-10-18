package com.anikulki.newsapp.repository

import com.anikulki.newsapp.data.local.db.DatabaseService
import com.anikulki.newsapp.data.local.db.entity.ArticleEntity
import com.anikulki.newsapp.data.remote.NetworkService
import com.anikulki.newsapp.data.remote.response.ArticlesResponse
import com.anikulki.newsapp.utils.common.State
import dagger.Lazy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
class ArticleRepository @Inject constructor(
    private val networkService: Lazy<NetworkService>,
    private val databaseService: DatabaseService
){
    fun getTopNews(): Flow<State<List<ArticleEntity>>> {
        return object: NetworkBoundRepository<List<ArticleEntity>, ArticlesResponse>(){

            override suspend fun saveRemoteData(response: ArticlesResponse) {
            }

            override fun getFromDatabase(): Flow<List<ArticleEntity>> {
                return databaseService.articlesDao().getAllArticles()
            }

            override suspend fun getFromRemoteServer(): Response<ArticlesResponse> {
                return networkService.get().callTopNews()
            }

        }.asFlow()
    }
}