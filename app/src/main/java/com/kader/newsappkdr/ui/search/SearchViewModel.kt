package com.kader.newsappkdr.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import com.kader.newsappkdr.data.model.ApiArticle
import com.kader.newsappkdr.data.repository.SearchRepository
import com.kader.newsappkdr.utils.Resource

class SearchViewModel(private val searchRepository: SearchRepository) : ViewModel() {

    private val _Api_articleList = MutableStateFlow<Resource<List<ApiArticle>>>(Resource.loading())

    val apiArticleList: StateFlow<Resource<List<ApiArticle>>> = _Api_articleList

    init {
//        fetchNews("us")
    }
    
    
//     fun fetchNews(query:String) {
//        viewModelScope.launch {
//            searchRepository.getSearchNews(query)
//                    .catch { e ->
//                        _articleList.value = Resource.error(e.toString())
//                    }
//                    .collect {
//                        _articleList.value = Resource.success(it)
//                    }
//
//        }
//    }

    fun fetchSearchNews(query: String)=searchRepository.getSearchNews(query)

    fun fetchDefault(country:String) {
        viewModelScope.launch {
            searchRepository.getDefaultNews(country)
                .catch { e ->
                    _Api_articleList.value = Resource.error(e.toString())
                }
                .collect {
                    _Api_articleList.value = Resource.success(it)
                }

        }
    }

}