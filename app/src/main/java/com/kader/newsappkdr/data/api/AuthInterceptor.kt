package com.kader.newsappkdr.data.api

import com.kader.newsappkdr.data.api.Networking.API_KEY
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader("Authorization", API_KEY)
        return chain.proceed(request.build())
    }
}