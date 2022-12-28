package com.kader.newsappkdr.ui.topheadline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.kader.newsappkdr.data.api.NetworkHelper
import com.kader.newsappkdr.data.local.entity.Article
import com.kader.newsappkdr.data.repository.TopHeadlineRepository
import com.kader.newsappkdr.utils.DispatcherProvider
import com.kader.newsappkdr.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagingApi::class)
class TopHeadlineViewModel(
    private val topHeadlineRepository: TopHeadlineRepository,
    private val networkHelper: NetworkHelper,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _apiArticleList = MutableStateFlow<Resource<PagingData<Article>>>(Resource.Loading())

    val apiArticleList: StateFlow<Resource<PagingData<Article>>> = _apiArticleList


//    private fun fetchNewsDirectlyFromDB() {
//        viewModelScope.launch(dispatcherProvider.main) {
//            topHeadlineRepository.getTopHeadlineDirectlyFromDB()
//                .flowOn(dispatcherProvider.io)
//                .catch { e ->
//                    _apiArticleList.value = Resource.Error(e.toString())
//                }
//                .collect {
//                    _apiArticleList.value = Resource.Success(it)
//                }
//
//        }
//
//    }

    private fun fetchNewsFromNetworkAndSaveInDB(country: String) {
        viewModelScope.launch(dispatcherProvider.main) {
            topHeadlineRepository.getTopHeadlinesPage(country)
                .flowOn(dispatcherProvider.io)
                .catch { e ->
                    _apiArticleList.value = Resource.Error(e.toString())
                }
                .collect {
                    _apiArticleList.value = Resource.Success(it)
                }

        }
    }

    fun fetchNews(country: String) {
//        if (networkHelper.isNetworkConnected()) {
            fetchNewsFromNetworkAndSaveInDB(country)
//        } else {
//            fetchNewsDirectlyFromDB()
//        }
    }


}