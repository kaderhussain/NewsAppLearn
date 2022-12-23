package com.kader.newsappkdr.di.module

import android.content.Context
import androidx.room.Room
import com.kader.newsappkdr.NewsApplication
import com.kader.newsappkdr.data.api.AuthInterceptor
import com.kader.newsappkdr.data.api.NetworkServices
import com.kader.newsappkdr.data.local.AppDatabase
import com.kader.newsappkdr.di.*
import com.kader.newsappkdr.utils.AppConstant
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: NewsApplication) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context {
        return application
    }


    @DatabaseName
    @Provides
    fun provideDatabaseName(): String="news_app_offline"

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context:Context,@DatabaseName name:String): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        name
    ).build()



    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = AppConstant.BASE_URL

    @ApiKey
    @Provides
    fun provideAPIKey(): String = AppConstant.API_KEY

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }


    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): NetworkServices {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
            .create(NetworkServices::class.java)
    }



}