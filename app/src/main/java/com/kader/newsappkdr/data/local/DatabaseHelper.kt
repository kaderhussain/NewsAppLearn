package com.kader.newsappkdr.data.local

import com.kader.newsappkdr.data.local.entity.Article
import kotlinx.coroutines.flow.Flow

interface DatabaseHelper {

    fun getTopHeadline(): Flow<List<Article>>

    fun deleteAll(): Int

    fun insertAll(users: List<Article>): Flow<Unit>

}