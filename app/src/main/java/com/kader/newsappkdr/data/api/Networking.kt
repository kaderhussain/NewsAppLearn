package com.kader.newsappkdr.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Networking {

    private const val BASE_URL = "https://newsapi.org/v2/"
    const val API_KEY = "2d3adfcf769f460d9411b62efa913852"

    fun create(): NetworkServices {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOKHttpService())
            .build()
            .create(NetworkServices::class.java)
    }


    fun createOKHttpService(): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
            .addInterceptor(AuthInterceptor())
        return builder.build()

    }


}