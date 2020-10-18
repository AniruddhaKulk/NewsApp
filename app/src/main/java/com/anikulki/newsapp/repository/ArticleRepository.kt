package com.anikulki.newsapp.repository

import com.anikulki.newsapp.data.local.db.DatabaseService
import com.anikulki.newsapp.data.local.db.entity.ArticleEntity
import com.anikulki.newsapp.data.remote.NetworkService
import com.anikulki.newsapp.data.remote.response.ArticlesResponse
import com.anikulki.newsapp.utils.common.Constants
import com.anikulki.newsapp.utils.common.State
import dagger.Lazy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
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
                val articlesList = articlesMapper(response)
                databaseService.articlesDao().insertArticles(articlesList)
            }

            override fun getFromDatabase(): Flow<List<ArticleEntity>> {
                return databaseService.articlesDao().getAllArticles()
            }

            override suspend fun getFromRemoteServer(): Response<ArticlesResponse> {
                return networkService.get().callTopNews()
            }

        }.asFlow()
    }

    private fun articlesMapper(response: ArticlesResponse): List<ArticleEntity> {
        val list = mutableListOf<ArticleEntity>()

        response.articles.forEach { article ->
            val articleEntity = ArticleEntity(
                type = Constants.TOP_NEWS,
                sourceName = article.source.name,
                author = article.author,
                title = article.title,
                description = article.description,
                url = article.url,
                imageUrl = article.imageUrl,
                content = article.content,
                publishedAt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
                    .parse(article.publishedAt)
            )

            list.add(articleEntity)
        }

        return list
    }
}