package com.kader.newsappkdr.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Article(
    @PrimaryKey val id: Int,
    @SerializedName("title")
    @ColumnInfo(name = "title")   val title: String = "",
    @SerializedName("description")
    @ColumnInfo(name = "description")   val description: String = "",
    @SerializedName("url")
    @ColumnInfo(name = "url")  val url: String = "",
    @SerializedName("urlToImage")
    @ColumnInfo(name = "imageUrl")  val imageUrl: String = "",
    @SerializedName("source")
    @ColumnInfo(name = "source")  val source: Source
)