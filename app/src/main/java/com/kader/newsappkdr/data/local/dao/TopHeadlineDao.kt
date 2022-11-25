package com.kader.newsappkdr.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.kader.newsappkdr.data.model.Article

@Dao
interface TopHeadlineDao {

    @Query("SELECT * FROM Article")
    fun getAllTopHeadline(): List<Article>

    @Insert
    fun insertAll(topHeadline: List<Article>)

    @Delete
    fun delete(topHeadline: Article)




}