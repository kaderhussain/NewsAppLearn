package com.kader.newsappkdr.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kader.newsappkdr.data.repository.CountriesRepository
import com.kader.newsappkdr.data.repository.LanguageRepository
import com.kader.newsappkdr.data.repository.NewsSourcesRepository
import com.kader.newsappkdr.data.repository.TopHeadlineRepository
import com.kader.newsappkdr.di.ActivityContext
import com.kader.newsappkdr.ui.base.ViewModelProviderFactory
import com.kader.newsappkdr.ui.countries.CountryAdapter
import com.kader.newsappkdr.ui.countries.CountryViewModel
import com.kader.newsappkdr.ui.languages.LanguageAdapter
import com.kader.newsappkdr.ui.languages.LanguageViewModel
import com.kader.newsappkdr.ui.news_sources.NewsSourcesAdapter
import com.kader.newsappkdr.ui.news_sources.NewsSourcesViewModel
import com.kader.newsappkdr.ui.topheadline.TopHeadlineAdapter
import com.kader.newsappkdr.ui.topheadline.TopHeadlineViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return activity
    }


    @Provides
    fun provideNewsListViewModel(topHeadlineRepository: TopHeadlineRepository): TopHeadlineViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(TopHeadlineViewModel::class) {
                TopHeadlineViewModel(topHeadlineRepository)
            })[TopHeadlineViewModel::class.java]
    }

    @Provides
    fun provideNewsSourcesViewModel(newsSourcesRepository: NewsSourcesRepository): NewsSourcesViewModel{
        return  ViewModelProvider(activity,
            ViewModelProviderFactory(NewsSourcesViewModel::class){
                NewsSourcesViewModel(newsSourcesRepository)
            })[NewsSourcesViewModel::class.java]
    }

    @Provides
    fun provideCountriesViewModel(countriesRepository: CountriesRepository): CountryViewModel{
        return  ViewModelProvider(activity,
            ViewModelProviderFactory(CountryViewModel::class){
                CountryViewModel(countriesRepository)
            })[CountryViewModel::class.java]
    }

    @Provides
    fun provideLanguageViewModel(languageRepository: LanguageRepository): LanguageViewModel{
        return  ViewModelProvider(activity,
            ViewModelProviderFactory(LanguageViewModel::class){
                LanguageViewModel(languageRepository)
            })[LanguageViewModel::class.java]
    }

    @Provides
    fun provideDummiesAdapter() = TopHeadlineAdapter(ArrayList())

    @Provides
    fun provideNewsSourcesAdapter() = NewsSourcesAdapter(ArrayList())

    @Provides
    fun provideCountriesAdapter() = CountryAdapter(ArrayList())

    @Provides
    fun provideLanguageAdapter() = LanguageAdapter(ArrayList())

}