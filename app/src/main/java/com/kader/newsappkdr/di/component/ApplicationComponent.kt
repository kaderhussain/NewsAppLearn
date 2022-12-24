package com.kader.newsappkdr.di.component

import android.content.Context
import com.kader.newsappkdr.NewsApplication
import com.kader.newsappkdr.data.api.NetworkHelper
import com.kader.newsappkdr.data.api.NetworkServices
import com.kader.newsappkdr.data.local.DatabaseHelperImpl
import com.kader.newsappkdr.data.repository.*
import com.kader.newsappkdr.di.ApplicationContext
import com.kader.newsappkdr.di.module.ApplicationModule
import com.kader.newsappkdr.utils.DefaultDispatcher
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: NewsApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkServices

    fun getDbHelper(): DatabaseHelperImpl

    fun getTopHeadlineRepository(): TopHeadlineRepository

    fun getNewsSourcesRepository(): NewsSourcesRepository

    fun getCountriesRepository(): CountriesRepository

    fun getLanguageRepository(): LanguageRepository

    fun getSearchNewRepository(): SearchRepository

    fun getNetworkHelper(): NetworkHelper

    fun getDefaultDispatcher(): DefaultDispatcher

}