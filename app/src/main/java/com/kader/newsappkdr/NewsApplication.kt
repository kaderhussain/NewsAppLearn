package com.kader.newsappkdr

import android.app.Application
import com.kader.newsappkdr.di.component.ApplicationComponent
import com.kader.newsappkdr.di.component.DaggerApplicationComponent
import com.kader.newsappkdr.di.module.ApplicationModule

class NewsApplication :Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

}