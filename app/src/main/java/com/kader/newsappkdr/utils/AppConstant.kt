package com.kader.newsappkdr.utils

import android.util.Log
import com.kader.newsappkdr.data.model.Country
import com.kader.newsappkdr.data.model.Language

object AppConstant {

    const val COUNTRY = "in"

    const val API_KEY = "9f6482a584804376874b848980b7a044"
    const val BASE_URL = "https://newsapi.org/v2/"
    const val DB_NAME = "news_app_offline"


    const val FINAL_PAGE = 5

    val LANGUAGES = arrayListOf(
        Language("en", "English"),
        Language("ar", "Arabic"),
        Language("fr", "France"),
        Language("zh", "Chinese"),
    )

    val COUNTRIES = arrayListOf(
        Country("in", "India"),
        Country("us", "USA"),
        Country("ca", "Cananda"),
        Country("ar", "Argentina"),
    )

    fun Logger(Tag: String, Value: String) {
        Log.e(Tag, Value);
    }
}