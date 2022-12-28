package com.kader.newsappkdr.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.kader.newsappkdr.data.api.NetworkServices
import com.kader.newsappkdr.data.local.AppDatabase
import com.kader.newsappkdr.data.local.entity.Article
import com.kader.newsappkdr.data.local.entity.ArticleRemoteKeys
import com.kader.newsappkdr.data.model.ApiArticle
import com.kader.newsappkdr.data.model.toArticle
import com.kader.newsappkdr.utils.AppConstant.FINAL_PAGE

@ExperimentalPagingApi
class ArticleRemoteMediator(
    private val networkServices: NetworkServices,
    private val appDatabase: AppDatabase,
    private val country: String
) : RemoteMediator<Int, Article>() {

    val articleDao = appDatabase.articleDao()
    val remoteKeysDao = appDatabase.remoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Article>
    ): MediatorResult{
        return try {

            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }


            val response = networkServices.getTopHeadlines(country, currentPage)
            val endOfpaginationReached = FINAL_PAGE == currentPage
            Log.e("ArticleRemoteMediator", "position: $currentPage")

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfpaginationReached) null else currentPage + 1

            appDatabase.withTransaction {

                if (loadType == LoadType.REFRESH) {
                    articleDao.deleteAll()
                    remoteKeysDao.deleteAll()
                }
                val apiArticles = response.apiArticles
                val articles = mutableListOf<Article>()
                for (apiArticle in apiArticles) {
                    articles.add(apiArticle.toArticle())
                }
                articleDao.insertAll(articles)

                val keys = response.apiArticles.map {
                        ArticleRemoteKeys(
                            id = it.url,
                            prevPage = prevPage,
                            nextPage = nextPage
                        )
                    }

                remoteKeysDao.insertAll(keys)
            }
            MediatorResult.Success(endOfpaginationReached)


        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Article>
    ): ArticleRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.url?.let { id ->
                remoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Article>
    ): ArticleRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { quote ->
                remoteKeysDao.getRemoteKeys(id = quote.url)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Article>
    ): ArticleRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { quote ->
                remoteKeysDao.getRemoteKeys(id = quote.url)
            }
    }

}