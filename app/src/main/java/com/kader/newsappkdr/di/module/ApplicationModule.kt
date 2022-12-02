package com.kader.newsappkdr.di.module

import android.content.Context
import com.kader.newsappkdr.NewsApplication
import com.kader.newsappkdr.data.api.AuthInterceptor
import com.kader.newsappkdr.data.api.NetworkServices
import com.kader.newsappkdr.data.api.Networking
import com.kader.newsappkdr.data.local.AppDatabase
import com.kader.newsappkdr.data.local.DatabaseBuilder
import com.kader.newsappkdr.data.local.DatabaseHelperImpl
import com.kader.newsappkdr.di.ApplicationContext
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: NewsApplication) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideNetworkService(): NetworkServices = Networking.create()

    @Provides
    @Singleton
    fun provideDatabaseBuilder(): AppDatabase = DatabaseBuilder.getInstance(application)

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

}