package com.kader.newsappkdr.data.model

import com.google.gson.annotations.SerializedName

data class NewsSources(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("url")
    val url: String = "",
)