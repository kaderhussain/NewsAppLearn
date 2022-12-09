package com.kader.newsappkdr.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Article(
    @PrimaryKey (autoGenerate = true) val id: Int=0,
    @ColumnInfo(name = "title")   val title: String,
    @ColumnInfo(name = "description")   val description: String?,
    @ColumnInfo(name = "url")  val url: String,
    @ColumnInfo(name = "imageUrl")  val imageUrl: String? ,
    @ColumnInfo(name = "source_id")  val source_id: String?,
    @ColumnInfo(name = "source_name")  val source_name: String
)