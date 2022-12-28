package com.kader.newsappkdr.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kader.newsappkdr.data.local.dao.ArticleDao
import com.kader.newsappkdr.data.local.dao.RemoteKeysDao
import com.kader.newsappkdr.data.local.entity.Article
import com.kader.newsappkdr.data.local.entity.ArticleRemoteKeys

@Database(entities = [Article::class,ArticleRemoteKeys::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao
    abstract fun remoteKeysDao(): RemoteKeysDao

}