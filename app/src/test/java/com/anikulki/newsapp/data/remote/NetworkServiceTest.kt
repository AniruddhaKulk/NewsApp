package com.anikulki.newsapp.data.remote

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue

@RunWith(JUnit4::class)
class NetworkServiceTest: BaseServiceTest(){

    private lateinit var networkService: NetworkService

    @Before
    @Throws(IOException::class)
    fun createService(){
        networkService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkService::class.java)
    }

    @Test
    @Throws(IOException::class, InterruptedException::class)
    fun callTopNews() = runBlocking {
        enqueueResponse("news.json")
        val response = networkService.callTopNews().body() ?: return@runBlocking

        assertThat(response, notNullValue())
        assertThat(response.totalResults, `is`(38))
        assertThat(response.status, `is`("ok"))

        // Check list
        val articles = response.articles
        assertThat(articles, notNullValue())

        // Check item 1
        val article1 = articles[0]
        assertThat(article1, notNullValue())
        assertThat(article1.author, `is`("Maeve Reston, CNN"))
        assertThat(article1.title, `is`("Trump in need of a game-changing moment as he meets Joe Biden for final debate - CNN"))
        assertThat(article1.description, `is`("President Donald Trump enters the final presidential debate on Thursday in need of a major shakeup that will change the trajectory of the race as he trails Joe Biden in both national polls and key swing states that will determine whether he has a path to vict…"))
        assertThat(article1.source.name, `is`("CNN"))
        assertThat(article1.url, `is`("https://www.cnn.com/2020/10/22/politics/2020-election-final-presidential-debate/index.html"))
        assertThat(article1.imageUrl, `is`("https://cdn.cnn.com/cnnnext/dam/assets/201022110005-05-trump-biden-split-super-tease.jpg"))
        assertThat(article1.publishedAt, `is`("2020-10-22T22:24:00Z"))
        assertThat(article1.content, `is`("(CNN)President Donald Trump enters the final presidential debate on Thursday in need of a major shakeup that will change the trajectory of the race as he trails Joe Biden in both national polls and k… [+4406 chars]"))
    }
}