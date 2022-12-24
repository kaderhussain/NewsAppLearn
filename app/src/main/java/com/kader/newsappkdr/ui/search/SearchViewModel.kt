package com.kader.newsappkdr.ui.search

import androidx.lifecycle.ViewModel
import com.kader.newsappkdr.data.repository.SearchRepository

class SearchViewModel(private val searchRepository: SearchRepository) : ViewModel() {

    fun fetchSearchNews(query: String) = searchRepository.getSearchNews(query)

}