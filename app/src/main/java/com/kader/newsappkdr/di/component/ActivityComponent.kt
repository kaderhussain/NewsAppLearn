package com.kader.newsappkdr.di.component

import android.content.Context
import com.kader.newsappkdr.data.repository.*
import com.kader.newsappkdr.di.ActivityContext
import dagger.Component
import com.kader.newsappkdr.di.ActivityScope
import com.kader.newsappkdr.di.ApplicationContext
import com.kader.newsappkdr.di.module.ActivityModule
import com.kader.newsappkdr.ui.countries.CountryActivity
import com.kader.newsappkdr.ui.languages.LanguageActivity
import com.kader.newsappkdr.ui.main.MainActivity
import com.kader.newsappkdr.ui.news_sources.NewsSourcesActivity
import com.kader.newsappkdr.ui.search.SearchActivity
import com.kader.newsappkdr.ui.topheadline.TopHeadlineActivity

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    @ActivityContext
    fun getContext(): Context

    fun injectTopHeadline(activity: TopHeadlineActivity)

    fun injectNewsSources(activity: NewsSourcesActivity)

    fun injectMain(activity: MainActivity)

    fun injectCountires(activity: CountryActivity)

    fun injectLanguage(activity: LanguageActivity)

    fun injectSearchNews(activity: SearchActivity)



}