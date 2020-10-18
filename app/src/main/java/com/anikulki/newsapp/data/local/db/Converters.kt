package com.anikulki.newsapp.data.local.db

import androidx.room.TypeConverter
import java.util.*

class Converters {

    @TypeConverter
    fun fromTimeStamp(value: Long): Date =
        Date(value)

    @TypeConverter
    fun toTimeStamp(date: Date): Long =
        date.time
}