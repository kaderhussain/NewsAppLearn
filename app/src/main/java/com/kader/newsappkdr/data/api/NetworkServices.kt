package com.kader.newsappkdr.data.api

import com.kader.newsappkdr.data.api.Networking.API_KEY
import com.kader.newsappkdr.data.model.CountriesResponse
import com.kader.newsappkdr.data.model.NewsSourcesResponse
import com.kader.newsappkdr.data.model.SearchNewssResponse
import com.kader.newsappkdr.data.model.TopHeadlinesResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkServices {

    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String): TopHeadlinesResponse

    @GET("top-headlines/sources")
    suspend fun getNewsSources(): NewsSourcesResponse

    @GET("top-headlines/sources")
    suspend fun getCountries(): CountriesResponse

    @GET("everything")
    suspend fun getSearchNews(@Query("q") search: String): SearchNewssResponse

}


//





