package com.android.dev.engineer.kotlin.compose.extension

import com.android.dev.engineer.kotlin.compose.data.api.TheMovieApi
import com.android.dev.engineer.kotlin.compose.data.di.NetworkModule.provideMoshi
import com.android.dev.engineer.kotlin.compose.data.di.NetworkModule.provideMoshiConverterFactory
import com.android.dev.engineer.kotlin.compose.data.di.NetworkModule.provideOkHttpClient
import com.android.dev.engineer.kotlin.compose.data.di.NetworkModule.provideRetrofitBuilder
import com.android.dev.engineer.kotlin.compose.data.di.NetworkModule.provideTheMovieApi
import com.android.dev.engineer.kotlin.compose.data.interceptor.ApiKeyInterceptor
import okhttp3.mockwebserver.MockWebServer

fun MockWebServer.toTheMovieApi(): TheMovieApi {
    val retrofitBuilder = provideRetrofitBuilder(
        baseUrl = url("").toString(),
        okHttpClient = provideOkHttpClient(apiKeyInterceptor = ApiKeyInterceptor()),
        moshiConverterFactory = provideMoshiConverterFactory(moshi = provideMoshi())
    )
    return provideTheMovieApi(retrofitBuilder = retrofitBuilder)
}