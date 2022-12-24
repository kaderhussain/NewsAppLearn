package com.kader.newsappkdr.data.repository

import com.kader.newsappkdr.data.api.NetworkServices
import com.kader.newsappkdr.data.model.NewsSources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsSourcesRepository @Inject constructor(private val networkService: NetworkServices) {

    fun getNewsSources(): Flow<List<NewsSources>> {
        return flow {
            emit(networkService.getNewsSources())
        }.map {
            it.sources
        }

    }

}