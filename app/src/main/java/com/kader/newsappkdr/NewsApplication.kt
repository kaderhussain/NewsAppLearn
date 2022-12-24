package com.kader.newsappkdr

import android.app.Application
import com.kader.newsappkdr.di.component.ApplicationComponent
import com.kader.newsappkdr.di.component.DaggerApplicationComponent
import com.kader.newsappkdr.di.module.ApplicationModule
import com.kader.newsappkdr.worker.WorkManagerHelper
import javax.inject.Inject

class NewsApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent

    @Inject
    lateinit var workManagerHelper: WorkManagerHelper

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
        workManagerHelper.setupWorker()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

}