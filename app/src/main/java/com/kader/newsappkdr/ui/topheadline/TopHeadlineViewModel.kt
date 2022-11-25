package com.kader.newsappkdr.ui.topheadline

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kader.newsappkdr.data.local.DatabaseHelperImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import com.kader.newsappkdr.data.model.Article
import com.kader.newsappkdr.data.repository.TopHeadlineRepository
import com.kader.newsappkdr.utils.AppConstant.COUNTRY
import com.kader.newsappkdr.utils.Resource

class TopHeadlineViewModel(private val topHeadlineRepository: TopHeadlineRepository,private val databaseHelperImpl: DatabaseHelperImpl) : ViewModel() {

    private val _articleList = MutableStateFlow<Resource<List<Article>>>(Resource.loading())

    val articleList: StateFlow<Resource<List<Article>>> = _articleList

    init {
        fetchNews("us")
    }
    
    
     fun fetchNews(country:String) {
        viewModelScope.launch {
            Log.e("country",country)
                topHeadlineRepository.getTopHeadlines(country)
                    .catch { e ->
                        _articleList.value = Resource.error(e.toString())
                    }
                    .collect {
                        _articleList.value = Resource.success(it)
                    }

        }
    }

}