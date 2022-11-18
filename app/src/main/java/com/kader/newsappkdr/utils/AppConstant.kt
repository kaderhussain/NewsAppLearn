package com.kader.newsappkdr.utils

import android.util.Log
import com.kader.newsappkdr.data.model.Countries
import com.kader.newsappkdr.data.model.Language

object AppConstant {

    const val COUNTRY="in"

    val LANGUAGES = arrayListOf(Language("en","English"),
        Language("ar","Arabic"),
        Language("fr","France"),
        Language("zh","Chinese"),
    )


    val COUNTRIES = arrayListOf(Countries("en","English"),
        Countries("in","India"),
        Countries("us","USA"),
        Countries("ca","Cananda"),
        Countries("ar","Arabic"),
    )


    fun Logger(Tag:String,Value:String){
        Log.e(Tag,Value);
    }
}