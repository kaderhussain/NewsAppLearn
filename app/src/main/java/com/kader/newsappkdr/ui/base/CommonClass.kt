package com.kader.newsappkdr.ui.base

import android.content.Context
import com.kader.newsappkdr.NewsApplication
import com.kader.newsappkdr.di.ApplicationContext
import com.kader.newsappkdr.di.component.DaggerActivityComponent
import com.kader.newsappkdr.di.module.ActivityModule
import javax.inject.Inject

class CommonClass(@ApplicationContext private val context: Context) {


//    private fun inject(context: Context) {
//        DaggerActivityComponent
//            .builder()
//            .applicationComponent((context as NewsApplication).applicationComponent)
//            .activityModule(ActivityModule(this))
//            .build()
//            .injectSearchNews(this)
//    }
}