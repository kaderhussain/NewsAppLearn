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


    fun fetchSearchNews(query: String)=searchRepository.getSearchNews(query)

}