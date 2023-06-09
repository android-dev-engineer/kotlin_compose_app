package com.android.dev.engineer.kotlin.compose.data.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AppPrefs

@Retention(AnnotationRetention.BINARY)
annotation class DataStoreCoroutineScope

@Retention(AnnotationRetention.BINARY)
annotation class TheMovieApiBaseUrl

@Retention(AnnotationRetention.BINARY)
annotation class ApiKey

@Retention(AnnotationRetention.BINARY)
annotation class EncryptedSharedPrefsKey