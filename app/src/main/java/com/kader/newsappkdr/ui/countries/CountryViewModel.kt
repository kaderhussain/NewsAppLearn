package com.kader.newsappkdr.ui.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kader.newsappkdr.data.model.Countries
import com.kader.newsappkdr.data.model.NewsSources
import com.kader.newsappkdr.data.repository.CountriesRepository
import com.kader.newsappkdr.data.repository.NewsSourcesRepository
import com.kader.newsappkdr.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CountryViewModel(private val countriesRepository:CountriesRepository):ViewModel() {
    private val _countryList = MutableStateFlow<Resource<List<Countries>>>(Resource.loading())

    val countryList: StateFlow<Resource<List<Countries>>> = _countryList
    
    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            countriesRepository.getCountries()
                .catch { e ->
                    _countryList.value = Resource.error(e.toString())
                }
                .collect {
                    _countryList.value = Resource.success(it)
                }
        }
    }
}
