package com.kader.newsappkdr.data.local

import com.kader.newsappkdr.data.model.Article
import kotlinx.coroutines.flow.Flow

interface DatabaseHelper {

    fun getTopHeadline(): Flow<List<Article>>

    fun getTopHeadlineList(): List<Article>

    fun insertAll(users: List<Article>): Flow<Unit>

}