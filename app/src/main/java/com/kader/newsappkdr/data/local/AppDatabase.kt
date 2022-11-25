package com.kader.newsappkdr.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kader.newsappkdr.data.local.dao.TopHeadlineDao
import com.kader.newsappkdr.data.model.Article

@Database(entities = [Article::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun topHeadlineDao(): TopHeadlineDao

}