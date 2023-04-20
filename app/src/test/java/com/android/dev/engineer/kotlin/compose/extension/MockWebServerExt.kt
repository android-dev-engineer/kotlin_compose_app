package com.android.dev.engineer.kotlin.compose.extension

import com.android.dev.engineer.kotlin.compose.data.api.TheMovieApi
import com.android.dev.engineer.kotlin.compose.data.di.NetworkModule
import com.android.dev.engineer.kotlin.compose.data.di.NetworkModule.provideRetrofitBuilder
import com.android.dev.engineer.kotlin.compose.data.interceptor.ApiKeyInterceptor
import okhttp3.mockwebserver.MockWebServer

fun MockWebServer.toTheMovieApi(): TheMovieApi {
    val moshi = NetworkModule.provideMoshi()
    val moshiConverterFactory = NetworkModule.provideMoshiConverterFactory(moshi = moshi)
    val okHttpClient = NetworkModule.provideOkHttpClient(apiKeyInterceptor = ApiKeyInterceptor())
    val retrofitBuilder = provideRetrofitBuilder(
        baseUrl = url("").toString(),
        okHttpClient = okHttpClient,
        moshiConverterFactory = moshiConverterFactory

    )
    return NetworkModule.provideTheMovieApi(retrofitBuilder = retrofitBuilder)
}