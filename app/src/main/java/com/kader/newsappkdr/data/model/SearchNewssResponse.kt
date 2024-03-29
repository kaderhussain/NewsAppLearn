package com.kader.newsappkdr.data.model

import com.google.gson.annotations.SerializedName

data class SearchNewssResponse(
    @SerializedName("status")
    val status: String = "",
    @SerializedName("totalResults")
    val totalResults: Int = 0,
    @SerializedName("articles")
    val apiArticles: List<ApiArticle> = ArrayList(),
)
