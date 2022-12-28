package com.kader.newsappkdr.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticleRemoteKeys (
        @PrimaryKey
        val id: String,
        val prevPage: Int?,
        val nextPage: Int?
        )