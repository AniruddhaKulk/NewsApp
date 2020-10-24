package com.anikulki.newsapp.data.local.db

import androidx.room.TypeConverter
import java.util.*

class Converters {

    @TypeConverter
    fun fromTimeStamp(value: Long?): Date? =
        value?.let { Date(it) }

    @TypeConverter
    fun dateToTimeStamp(date: Date): Long? =
        date?.time
}