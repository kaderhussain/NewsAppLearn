package com.kader.newsappkdr.worker

import android.content.Context
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.kader.newsappkdr.di.ApplicationContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

class WorkManagerHelper @Inject constructor(@ApplicationContext val context: Context) {

     fun setupWorker(){
        val constraint= Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workRequest = PeriodicWorkRequest.Builder(MidnightWorker::class.java,22, TimeUnit.HOURS)
            .setInitialDelay(21, TimeUnit.HOURS)
            .setConstraints(constraint)
            .build()
        WorkManager.getInstance(context).enqueue(workRequest)
    }

}