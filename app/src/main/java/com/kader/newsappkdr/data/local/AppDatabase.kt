package com.kader.newsappkdr.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kader.newsappkdr.data.local.dao.TopHeadlineDao
import com.kader.newsappkdr.data.local.entity.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun topHeadlineDao(): TopHeadlineDao

}