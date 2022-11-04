package com.kader.newsappkdr.data.repository

import com.kader.newsappkdr.data.api.NetworkServices
import com.kader.newsappkdr.data.model.Countries
import com.kader.newsappkdr.data.model.Language
import com.kader.newsappkdr.utils.AppConstant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguageRepository @Inject constructor(){
    fun getLanguages(): Flow<List<Language>> {
        return flow {
            emit(AppConstant.LANGUAGES)
        }
    }
}