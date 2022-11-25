package com.kader.newsappkdr.data.local

import com.kader.newsappkdr.data.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {

    override fun getTopHeadline(): Flow<List<Article>> = flow {
        emit(appDatabase.topHeadlineDao().getAllTopHeadline())
    }

    override fun getTopHeadlineList(): List<Article> = appDatabase.topHeadlineDao().getAllTopHeadline()


    override fun insertAll(users: List<Article>): Flow<Unit> = flow {
        appDatabase.topHeadlineDao().insertAll(users)
        emit(Unit)
    }

}