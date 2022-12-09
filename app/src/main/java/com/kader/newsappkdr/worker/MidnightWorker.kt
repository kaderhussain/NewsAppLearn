package com.kader.newsappkdr.worker

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.kader.newsappkdr.NewsApplication
import com.kader.newsappkdr.data.api.NetworkHelper
import com.kader.newsappkdr.di.component.DaggerActivityComponent
import com.kader.newsappkdr.di.module.ActivityModule
import com.kader.newsappkdr.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class MidnightWorker(private val context: Context,params: WorkerParameters): Worker(context,params) {


    val repository = (context as NewsApplication).applicationComponent

    override fun doWork(): Result {
        GlobalScope.launch {
            repository.getTopHeadlineRepository().getTopHeadlines("us")
                .catch {  }
                .collect{

                }
        }

        return Result.success();
    }



}