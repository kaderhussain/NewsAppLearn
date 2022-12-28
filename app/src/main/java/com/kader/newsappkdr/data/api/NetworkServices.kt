package com.kader.newsappkdr.data.api

import com.kader.newsappkdr.data.model.CountriesResponse
import com.kader.newsappkdr.data.model.NewsSourcesResponse
import com.kader.newsappkdr.data.model.SearchNewsResponse
import com.kader.newsappkdr.data.model.TopHeadlinesResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkServices {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("page") pageNumber: Int = 1
    ): TopHeadlinesResponse

    @GET("top-headlines/sources")
    suspend fun getNewsSources(): NewsSourcesResponse

    @GET("top-headlines/sources")
    suspend fun getCountries(): CountriesResponse

    @GET("everything")
    suspend fun getSearchNews(
        @Query("q") search: String="",
        @Query("page") pageNumber: Int = 1,
    ): SearchNewsResponse

}





