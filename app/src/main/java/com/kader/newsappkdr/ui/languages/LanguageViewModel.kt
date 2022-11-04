package com.kader.newsappkdr.ui.languages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kader.newsappkdr.data.model.Countries
import com.kader.newsappkdr.data.model.Language
import com.kader.newsappkdr.data.repository.CountriesRepository
import com.kader.newsappkdr.data.repository.LanguageRepository
import com.kader.newsappkdr.data.repository.NewsSourcesRepository
import com.kader.newsappkdr.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class LanguageViewModel(private val languageRepository: LanguageRepository):ViewModel() {
    private val _languageList = MutableStateFlow<Resource<List<Language>>>(Resource.loading())

    val languageList: StateFlow<Resource<List<Language>>> = _languageList

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            languageRepository.getLanguages()
                .catch { e ->
                    _languageList.value = Resource.error(e.toString())
                }
                .collect {
                    _languageList.value = Resource.success(it)
                }
        }
    }
}
