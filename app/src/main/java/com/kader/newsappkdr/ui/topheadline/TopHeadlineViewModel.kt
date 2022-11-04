package com.kader.newsappkdr.ui.topheadline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import com.kader.newsappkdr.data.model.Article
import com.kader.newsappkdr.data.repository.TopHeadlineRepository
import com.kader.newsappkdr.utils.AppConstant.COUNTRY
import com.kader.newsappkdr.utils.Resource

class TopHeadlineViewModel(private val topHeadlineRepository: TopHeadlineRepository) : ViewModel() {

    private val _articleList = MutableStateFlow<Resource<List<Article>>>(Resource.loading())

    val articleList: StateFlow<Resource<List<Article>>> = _articleList

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            topHeadlineRepository.getTopHeadlines(COUNTRY)
                .catch { e ->
                    _articleList.value = Resource.error(e.toString())
                }
                .collect {
                    _articleList.value = Resource.success(it)
                }
        }
    }

}