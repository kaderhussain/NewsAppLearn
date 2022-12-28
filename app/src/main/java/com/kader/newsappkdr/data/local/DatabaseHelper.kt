package com.kader.newsappkdr.data.local

import androidx.paging.PagingSource
import com.kader.newsappkdr.data.local.entity.Article
import kotlinx.coroutines.flow.Flow

interface DatabaseHelper {

    fun getTopHeadline(): Flow<List<Article>>

    fun getAllTopHeadlinePage(): PagingSource<Int, Article>

    fun deleteAll(): Int

    fun insertAll(article: List<Article>): Flow<Unit>

}