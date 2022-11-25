package com.kader.newsappkdr.utils

import android.util.Log
import com.kader.newsappkdr.data.model.Country
import com.kader.newsappkdr.data.model.Language

object AppConstant {

    const val COUNTRY="in"

    val LANGUAGES = arrayListOf(Language("en","English"),
        Language("ar","Arabic"),
        Language("fr","France"),
        Language("zh","Chinese"),
    )


    val COUNTRIES = arrayListOf(
        Country("in","India"),
        Country("us","USA"),
        Country("ca","Cananda"),
        Country("ar","Arabic"),
    )


    fun Logger(Tag:String,Value:String){
        Log.e(Tag,Value);
    }
}