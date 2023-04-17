package com.android.dev.engineer.kotlin.compose.extension

import com.android.dev.engineer.kotlin.compose.data.api.UnifiedApi
import com.android.dev.engineer.kotlin.compose.data.di.NetworkModule
import com.android.dev.engineer.kotlin.compose.data.di.NetworkModule.provideRetrofitBuilder
import com.android.dev.engineer.kotlin.compose.data.interceptor.ApiKeyInterceptor
import okhttp3.mockwebserver.MockWebServer

fun MockWebServer.unifiedApi(): UnifiedApi {
    val moshi = NetworkModule.provideMoshi()
    val moshiConverterFactory = NetworkModule.provideMoshiConverterFactory(moshi = moshi)
    val okHttpClient = NetworkModule.provideOkHttpClient(apiKeyInterceptor = ApiKeyInterceptor())
    val retrofitBuilder = provideRetrofitBuilder(
        okHttpClient = okHttpClient,
        moshiConverterFactory = moshiConverterFactory,
        baseUrl = url("").toString()

    )
    return NetworkModule.provideUnifiedApi(retrofitBuilder = retrofitBuilder)
}