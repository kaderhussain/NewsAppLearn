package com.kader.newsappkdr

import android.app.Application
import androidx.constraintlayout.widget.ConstraintSet
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.kader.newsappkdr.di.component.ApplicationComponent
import com.kader.newsappkdr.di.component.DaggerApplicationComponent
import com.kader.newsappkdr.di.module.ApplicationModule
import com.kader.newsappkdr.worker.MidnightWorker
import com.kader.newsappkdr.worker.WorkManagerHelper
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NewsApplication :Application() {
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