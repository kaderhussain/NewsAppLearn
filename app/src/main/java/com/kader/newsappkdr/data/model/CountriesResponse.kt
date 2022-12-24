package com.kader.newsappkdr.data.model

import com.google.gson.annotations.SerializedName

data class CountriesResponse(
    @SerializedName("status")
    val status: String = "",
    @SerializedName("sources")
    val sources: List<Country> = ArrayList(),
)