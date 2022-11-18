package com.kader.newsappkdr.data.repository

import com.kader.newsappkdr.data.api.NetworkServices
import com.kader.newsappkdr.data.model.Article
import com.kader.newsappkdr.data.model.NewsSources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(private val networkService: NetworkServices) {

    fun getSearchNews(query: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getSearchNews(query))
        }.map {
            it.articles
        }
    }


    fun getDefaultNews(query: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getTopHeadlines(query))
        }.map {
            it.articles
        }
    }
}