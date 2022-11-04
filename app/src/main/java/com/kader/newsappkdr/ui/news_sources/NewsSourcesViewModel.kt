package com.kader.newsappkdr.ui.news_sources

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kader.newsappkdr.data.model.Article
import com.kader.newsappkdr.data.model.NewsSources
import com.kader.newsappkdr.data.repository.NewsSourcesRepository
import com.kader.newsappkdr.utils.AppConstant
import com.kader.newsappkdr.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class NewsSourcesViewModel(private val newsSourcesRepository: NewsSourcesRepository):ViewModel() {
    private val _newSourceList = MutableStateFlow<Resource<List<NewsSources>>>(Resource.loading())

    val newSourceList: StateFlow<Resource<List<NewsSources>>> = _newSourceList

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            newsSourcesRepository.getNewsSources()
                .catch { e ->
                    _newSourceList.value = Resource.error(e.toString())
                }
                .collect {
                    _newSourceList.value = Resource.success(it)
                }
        }
    }
}