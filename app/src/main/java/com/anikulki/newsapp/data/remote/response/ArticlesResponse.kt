package com.anikulki.newsapp.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ArticlesResponse(
    @Expose
    @SerializedName("articles")
    val articles: List<Article>,
    @Expose
    @SerializedName("status")
    val status: String,
    @Expose
    @SerializedName("totalResults")
    val totalResults: Int
)

data class Article(
    @Expose
    @SerializedName("author")
    val author: String?,
    @Expose
    @SerializedName("content")
    val content: String?,
    @Expose
    @SerializedName("description")
    val description: String?,
    @Expose
    @SerializedName("publishedAt")
    val publishedAt: String,
    @Expose
    @SerializedName("source")
    val source: Source,
    @Expose
    @SerializedName("title")
    val title: String,
    @Expose
    @SerializedName("url")
    val url: String,
    @Expose
    @SerializedName("urlToImage")
    val imageUrl: String?
)

data class Source(
    @Expose
    @SerializedName("id")
    val id: String?,
    @Expose
    @SerializedName("name")
    val name: String
)