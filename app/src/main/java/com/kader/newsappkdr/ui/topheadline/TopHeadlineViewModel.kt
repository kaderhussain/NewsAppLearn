package com.kader.newsappkdr.ui.topheadline

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kader.newsappkdr.data.local.DatabaseHelperImpl
import com.kader.newsappkdr.data.local.entity.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import com.kader.newsappkdr.data.model.ApiArticle
import com.kader.newsappkdr.data.repository.TopHeadlineRepository
import com.kader.newsappkdr.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn


class TopHeadlineViewModel(private val topHeadlineRepository: TopHeadlineRepository) : ViewModel() {

    private val _Api_articleList = MutableStateFlow<Resource<List<Article>>>(Resource.loading())

    val apiArticleList: StateFlow<Resource<List<Article>>> = _Api_articleList


    private fun fetchNewsDirectlyFromDB(){
        viewModelScope.launch {
            topHeadlineRepository.getTopHeadlineDirectlyFromDB()
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    _Api_articleList.value = Resource.error(e.toString())
                }
                .collect {
                    _Api_articleList.value = Resource.success(it)
                }

        }
    }
    
     private fun fetchNewsFromNetworkAndSaveInDB(country:String) {
        viewModelScope.launch {
            Log.e("country",country)
                topHeadlineRepository.getTopHeadlines(country)
                    .flowOn(Dispatchers.IO)
                    .catch { e ->
                        _Api_articleList.value = Resource.error(e.toString())
                    }
                    .collect {
                        _Api_articleList.value = Resource.success(it)
                    }

        }
    }

    fun fetchNews(country :String,isInternetConnected: Boolean){
        if(isInternetConnected){
            fetchNewsFromNetworkAndSaveInDB(country)
        }else{
            fetchNewsDirectlyFromDB()
        }
    }





}