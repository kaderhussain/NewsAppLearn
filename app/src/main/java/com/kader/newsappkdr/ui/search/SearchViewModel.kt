package com.kader.newsappkdr.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import com.kader.newsappkdr.data.model.Article
import com.kader.newsappkdr.data.repository.SearchRepository
import com.kader.newsappkdr.data.repository.TopHeadlineRepository
import com.kader.newsappkdr.utils.AppConstant.COUNTRY
import com.kader.newsappkdr.utils.Resource

class SearchViewModel(private val searchRepository: SearchRepository) : ViewModel() {

    private val _articleList = MutableStateFlow<Resource<List<Article>>>(Resource.loading())

    val articleList: StateFlow<Resource<List<Article>>> = _articleList

    init {
        fetchNews("us")
    }
    
    
     fun fetchNews(query:String) {
        viewModelScope.launch {
            searchRepository.getSearchNews(query)
                    .catch { e ->
                        _articleList.value = Resource.error(e.toString())
                    }
                    .collect {
                        _articleList.value = Resource.success(it)
                    }

        }
    }

    fun fetchDefault(country:String) {
        viewModelScope.launch {
            searchRepository.getDefaultNews(country)
                .catch { e ->
                    _articleList.value = Resource.error(e.toString())
                }
                .collect {
                    _articleList.value = Resource.success(it)
                }

        }
    }

}