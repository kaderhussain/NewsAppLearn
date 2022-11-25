package com.kader.newsappkdr.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Source(
    @PrimaryKey
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String = "",
)
