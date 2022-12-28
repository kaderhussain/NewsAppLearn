package com.kader.newsappkdr.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.kader.newsappkdr.data.model.ApiArticle
import com.kader.newsappkdr.data.repository.SearchRepository
import com.kader.newsappkdr.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SearchViewModel(private val searchRepository: SearchRepository) : ViewModel() {

    fun fetchSearchNews(query: String) = searchRepository.getSearchNewsPage(query).cachedIn(viewModelScope)

}