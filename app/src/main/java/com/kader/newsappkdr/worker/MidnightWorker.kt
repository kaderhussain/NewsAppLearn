package com.kader.newsappkdr.worker

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.kader.newsappkdr.NewsApplication
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MidnightWorker(private val context: Context, params: WorkerParameters) :
    Worker(context, params) {


    val repository = (context as NewsApplication).applicationComponent

    @OptIn(ExperimentalPagingApi::class)
    override fun doWork(): Result {
        GlobalScope.launch {
            repository.getTopHeadlineRepository().getTopHeadlinesPage("us")
                .catch { }
                .collect {

                }
        }

        return Result.success();
    }


}