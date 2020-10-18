package com.anikulki.newsapp.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anikulki.newsapp.data.local.db.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ArticlesDao {

    /**
     * Insert all the articles
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<ArticleEntity>)

    /**
     * Deletes all the articles
     */

    @Query("DELETE FROM articles")
    suspend fun deleteAllArticles()

    /**
     * Fetches all the articles.
     * @return [Flow]
     */

    @Query("SELECT * FROM articles")
    fun getAllArticles(): Flow<List<ArticleEntity>>

}