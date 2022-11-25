package com.kader.newsappkdr.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import com.kader.newsappkdr.data.api.NetworkServices
import com.kader.newsappkdr.data.local.DatabaseHelperImpl
import com.kader.newsappkdr.data.local.entity.Article
import com.kader.newsappkdr.data.model.ApiArticle
import com.kader.newsappkdr.data.model.toArticle
import kotlinx.coroutines.flow.flatMapConcat
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopHeadlineRepository @Inject constructor(private val networkService: NetworkServices,private val databaseHelperImpl: DatabaseHelperImpl) {

    fun getTopHeadlines(country: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getTopHeadlines(country))
            }
            .map {
               val apiArticles =  it.apiArticles
               val articles = mutableListOf<Article>()
               for (apiArticle in apiArticles){
                   articles.add(apiArticle.toArticle())
               }
                return@map articles
             }
            .flatMapConcat { articles ->

                return@flatMapConcat flow {
                    emit(databaseHelperImpl.deleteAll())
                }.map {
                    return@map articles
                }
            }
            .flatMapConcat {

                return@flatMapConcat databaseHelperImpl.insertAll(it)
            }.flatMapConcat {
                return@flatMapConcat databaseHelperImpl.getTopHeadline()
            }
    }

    fun getTopHeadlineDirectlyFromDB() :Flow<List<Article>> {
        return  databaseHelperImpl.getTopHeadline()
    }

}