package com.kader.newsappkdr.ui.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kader.newsappkdr.data.model.Country
import com.kader.newsappkdr.data.repository.CountriesRepository
import com.kader.newsappkdr.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CountryViewModel(private val countriesRepository: CountriesRepository) : ViewModel() {
    private val _countryList = MutableStateFlow<Resource<List<Country>>>(Resource.Loading())

    var countryList: StateFlow<Resource<List<Country>>> = _countryList

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            countriesRepository.getCountries()
                .catch { e ->
                    _countryList.value = Resource.Error(e.toString())
                }
                .collect {
                    _countryList.value = Resource.Success(it)
                }
        }
    }
}
