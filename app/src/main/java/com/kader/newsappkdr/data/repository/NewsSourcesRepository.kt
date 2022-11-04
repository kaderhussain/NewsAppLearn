package com.kader.newsappkdr.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import com.kader.newsappkdr.data.api.NetworkServices
import com.kader.newsappkdr.data.model.Article
import com.kader.newsappkdr.data.model.NewsSources
import com.kader.newsappkdr.data.model.NewsSourcesResponse
import javax.inject.Inject

class NewsSourcesRepository  @Inject constructor(private val networkService: NetworkServices){

    fun getNewsSources():Flow<List<NewsSources>>{
        return flow {
            emit(networkService.getNewsSources())
        }.map {
            it.sources
        }

    }

}