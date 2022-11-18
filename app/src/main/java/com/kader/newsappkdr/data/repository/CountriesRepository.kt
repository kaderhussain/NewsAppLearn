package com.kader.newsappkdr.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import com.kader.newsappkdr.data.api.NetworkServices
import com.kader.newsappkdr.data.model.*
import com.kader.newsappkdr.utils.AppConstant
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountriesRepository  @Inject constructor(private val networkService: NetworkServices){

    fun getCountries():Flow<List<Countries>>{
        return flow {
            emit(AppConstant.COUNTRIES)
        }
    }
}