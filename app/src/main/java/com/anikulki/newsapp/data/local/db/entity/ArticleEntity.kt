package com.anikulki.newsapp.data.local.db.entity

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "articles")
data class ArticleEntity(

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "source_name")
    @Nullable
    val sourceName: String?,

    @ColumnInfo(name = "author")
    @Nullable
    val author: String?,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    @Nullable
    val description: String?,

    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "urlToImage")
    @Nullable
    val imageUrl: String?,

    @ColumnInfo(name = "content")
    @Nullable
    val content: String?,

    @ColumnInfo(name = "publish_time")
    val publishedAt: Date?

){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}