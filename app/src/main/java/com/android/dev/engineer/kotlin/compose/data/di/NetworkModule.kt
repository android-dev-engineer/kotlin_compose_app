package com.android.dev.engineer.kotlin.compose.data.di

import com.android.dev.engineer.kotlin.compose.data.api.UnifiedApi
import com.android.dev.engineer.kotlin.compose.data.api.UnifiedApi.Companion.BASE_API_URL
import com.android.dev.engineer.kotlin.compose.data.interceptor.ApiKeyInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val READ_TIMEOUT_SECS = 60L
    private const val CONNECT_TIMEOUT_SECS = 60L
    private const val THE_MOVIE_API_BASE_URL_KEY = "THE_MOVIE_API_BASE_URL_KEY"

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Singleton
    @Provides
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory {
        return MoshiConverterFactory.create(moshi)
    }

    @Singleton
    @Provides
    fun provideApiKeyInterceptor(apiKeyInterceptor: ApiKeyInterceptor): Interceptor {
        return apiKeyInterceptor
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        apiKeyInterceptor: ApiKeyInterceptor
    ): OkHttpClient {
        return OkHttpClient().newBuilder()
            .connectTimeout(CONNECT_TIMEOUT_SECS, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT_SECS, TimeUnit.SECONDS)
            .addInterceptor(apiKeyInterceptor)
            .build()
    }

    @Named(THE_MOVIE_API_BASE_URL_KEY)
    @Singleton
    @Provides
    fun provideBaseApiUrl(): String {
        return BASE_API_URL
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory,
        @Named(THE_MOVIE_API_BASE_URL_KEY) baseUrl: String
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(moshiConverterFactory)
            .client(okHttpClient)
    }

    @Singleton
    @Provides
    fun provideUnifiedApi(retrofitBuilder: Retrofit.Builder): UnifiedApi {
        return retrofitBuilder.build().create(UnifiedApi::class.java)
    }
}