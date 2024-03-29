package com.kader.newsappkdr.data.local


import com.kader.newsappkdr.data.local.entity.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseHelperImpl @Inject constructor(private val appDatabase: AppDatabase) :
    DatabaseHelper {

    override fun getTopHeadline(): Flow<List<Article>> =
        appDatabase.articleDao().getAllTopHeadline()

    override fun deleteAll(): Int = appDatabase.articleDao().deleteAll()

    override fun insertAll(article: List<Article>): Flow<Unit> = flow {
        appDatabase.articleDao().insertAll(article)
        emit(Unit)
    }

}