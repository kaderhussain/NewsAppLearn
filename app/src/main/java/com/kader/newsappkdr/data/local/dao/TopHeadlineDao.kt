package com.kader.newsappkdr.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.kader.newsappkdr.data.local.entity.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface TopHeadlineDao {

    @Query("SELECT * FROM Article")
    fun getAllTopHeadline(): Flow<List<Article>>

    @Insert
    fun insertAll(topHeadline: List<Article>)

    @Query("Delete from Article")
    fun deleteAll(): Int


}