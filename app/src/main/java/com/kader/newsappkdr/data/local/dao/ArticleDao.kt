package com.kader.newsappkdr.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kader.newsappkdr.data.local.entity.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM Article")
    fun getAllTopHeadline(): Flow<List<Article>>

    @Query("SELECT * FROM Article")
    fun getAllTopHeadlinePage(): PagingSource<Int,Article>

    @Insert
    suspend fun insertAll(topHeadline: List<Article>)

    @Query("Delete from Article")
    fun deleteAll(): Int


}