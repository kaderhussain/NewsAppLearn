package com.kader.newsappkdr.utils

import android.util.Log
import com.kader.newsappkdr.data.model.Language

object AppConstant {

    const val COUNTRY="in"

    val LANGUAGES = arrayListOf(Language("en","English"),
        Language("ar","Arabic"),
        Language("fr","France"),
        Language("zh","Chinese"),
    )

    const val Test=1_00_000


    fun Logger(Tag:String,Value:String){
        Log.e(Tag,Value);
    }
}