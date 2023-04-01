package com.android.dev.engineer.kotlin.compose.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.android.dev.engineer.kotlin.compose.data.local.AppDataStore
import com.android.dev.engineer.kotlin.compose.data.local.AppDataStoreImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @AppPrefs
    @Singleton
    @Provides
    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { appContext.preferencesDataStoreFile(AppDataStore.APP_DATASTORE_KEY) }
        )
    }

    @Singleton
    @Provides
    fun provideAppDataStore(appDataStoreImpl: AppDataStoreImpl): AppDataStore {
        return appDataStoreImpl
    }
}