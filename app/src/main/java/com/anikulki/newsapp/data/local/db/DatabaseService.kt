package com.anikulki.newsapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.anikulki.newsapp.data.local.db.dao.ArticlesDao
import com.anikulki.newsapp.data.local.db.entity.ArticleEntity
import javax.inject.Singleton


@Singleton
@Database(
    entities = [
        ArticleEntity::class
    ],
    exportSchema = false,
    version = 1
)
@TypeConverters(Converters::class)
abstract class DatabaseService : RoomDatabase() {

    abstract fun articlesDao(): ArticlesDao
}