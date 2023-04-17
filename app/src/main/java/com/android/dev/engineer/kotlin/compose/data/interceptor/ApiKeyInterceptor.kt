package com.android.dev.engineer.kotlin.compose.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiKeyInterceptor @Inject constructor() : Interceptor {
    companion object {
        const val API_KEY_NAME = "api_key"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(API_KEY_NAME, "")
            .build()
        val requestBuilder = original.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}