package com.anikulki.newsapp.data.local.db.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.anikulki.newsapp.data.local.db.DatabaseService
import com.anikulki.newsapp.data.local.db.entity.ArticleEntity
import com.anikulki.newsapp.utils.common.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import org.junit.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.MatcherAssert.assertThat
import java.util.*

@RunWith(AndroidJUnit4::class)
class ArticlesDaoTest: DaoTest<DatabaseService>(DatabaseService::class.java){

    @Test
    @Throws(InterruptedException::class)
    fun insertTopNewsArticleAndRead(): Unit = runBlocking {
        val input = listOf(ArticleEntity(
            Constants.TOP_NEWS,
            "Source 1",
            "Author 1",
            "Title 1",
            "Description 1",
            "Url 1",
            "Image Url 1",
            "Content 1",
            Date()),
            ArticleEntity(
                Constants.TOP_NEWS,
                "Source 2",
                "Author 2",
                "Title 2",
                "Description 2",
                "Url 2",
                "Image Url 2",
                "Content 2",
                Date()))

        db.articlesDao().insertArticles(input)

        db.articlesDao().getAllArticles().assertItems(input)
    }
}


//Asserts only the [input] items by just taking that many from the stream
private suspend fun <T> Flow<T>.assertItems(vararg input: T) {
    assertEquals(input.toList(), this.take(input.size).toList())
}
