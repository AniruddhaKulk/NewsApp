package com.anikulki.newsapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp
import java.util.*

@HiltAndroidApp
class NewsApplication: Application(){


    override fun onCreate() {
        super.onCreate()

        val mode = if (isNight()) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }

        AppCompatDelegate.setDefaultNightMode(mode)
    }


    private fun isNight(): Boolean {
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        //return (currentHour <= 8 || currentHour >= 19)
        return false
    }
}