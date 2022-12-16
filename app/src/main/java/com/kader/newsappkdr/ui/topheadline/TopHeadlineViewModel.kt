package com.kader.newsappkdr.ui.topheadline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kader.newsappkdr.data.api.NetworkHelper
import com.kader.newsappkdr.data.local.entity.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import com.kader.newsappkdr.data.repository.TopHeadlineRepository
import com.kader.newsappkdr.utils.DispatcherProvider
import com.kader.newsappkdr.utils.Resource
import kotlinx.coroutines.flow.flowOn


class TopHeadlineViewModel(private val topHeadlineRepository: TopHeadlineRepository,private val networkHelper:NetworkHelper,private val dispatcherProvider: DispatcherProvider) : ViewModel() {

    private val _apiArticleList = MutableStateFlow<Resource<List<Article>>>(Resource.loading())

    val apiArticleList: StateFlow<Resource<List<Article>>> = _apiArticleList

    private fun fetchNewsDirectlyFromDB(){
        viewModelScope.launch(dispatcherProvider.main) {
            topHeadlineRepository.getTopHeadlineDirectlyFromDB()
                .flowOn(dispatcherProvider.io)
                .catch { e ->
                    _apiArticleList.value = Resource.error(e.toString())
                }
                .collect {
                    _apiArticleList.value = Resource.success(it)
                }

        }

    }

     private fun fetchNewsFromNetworkAndSaveInDB(country:String) {
        viewModelScope.launch (dispatcherProvider.main){
                topHeadlineRepository.getTopHeadlines(country)
                    .flowOn(dispatcherProvider.io)
                    .catch { e ->
                        _apiArticleList.value = Resource.error(e.toString())
                    }
                    .collect {
                        _apiArticleList.value = Resource.success(it)
                    }

        }
    }

    fun fetchNews(country :String){
        if(networkHelper.isNetworkconnected()){
            fetchNewsFromNetworkAndSaveInDB(country)
        }else{
            fetchNewsDirectlyFromDB()
        }
    }





}