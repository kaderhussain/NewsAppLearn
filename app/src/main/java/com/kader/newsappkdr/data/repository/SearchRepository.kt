package com.kader.newsappkdr.data.repository

import com.kader.newsappkdr.data.api.NetworkServices
import com.kader.newsappkdr.data.model.ApiArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(private val networkService: NetworkServices) {

    fun getSearchNews(query: String): Flow<List<ApiArticle>> {
        return flow {
            emit(networkService.getSearchNews(query))
        }.map {
            it.apiArticles
        }
    }


    fun getDefaultNews(query: String): Flow<List<ApiArticle>> {
        return flow {
            emit(networkService.getTopHeadlines(query))
        }.map {
            it.apiArticles
        }
    }
}