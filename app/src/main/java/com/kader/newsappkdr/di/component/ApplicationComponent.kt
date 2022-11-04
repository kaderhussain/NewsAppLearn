package com.kader.newsappkdr.di.component

import android.content.Context
import dagger.Component
import com.kader.newsappkdr.NewsApplication
import com.kader.newsappkdr.data.api.NetworkServices
import com.kader.newsappkdr.data.repository.CountriesRepository
import com.kader.newsappkdr.data.repository.LanguageRepository
import com.kader.newsappkdr.data.repository.NewsSourcesRepository
import com.kader.newsappkdr.data.repository.TopHeadlineRepository
import com.kader.newsappkdr.di.ApplicationContext
import com.kader.newsappkdr.di.module.ApplicationModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: NewsApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkServices

    fun getTopHeadlineRepository(): TopHeadlineRepository

    fun getNewsSourcesRepository(): NewsSourcesRepository

    fun getCountriesRepository(): CountriesRepository

    fun getLanguageRepository(): LanguageRepository


}