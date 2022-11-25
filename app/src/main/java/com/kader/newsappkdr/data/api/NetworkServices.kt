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

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String): TopHeadlinesResponse

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines/sources")
    suspend fun getNewsSources(): NewsSourcesResponse

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines/sources")
    suspend fun getCountries(): CountriesResponse

    @Headers("X-Api-Key: $API_KEY")
    @GET("everything")
    suspend fun getSearchNews(@Query("q") search: String): SearchNewssResponse

}


//





