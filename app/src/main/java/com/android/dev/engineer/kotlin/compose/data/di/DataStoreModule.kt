package com.android.dev.engineer.kotlin.compose.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.android.dev.engineer.kotlin.compose.data.datastore.app.AppDataStore
import com.android.dev.engineer.kotlin.compose.data.datastore.app.AppDataStoreImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @DataStoreCoroutineScope
    @Singleton
    @Provides
    fun provideDataSoreCoroutineScope(
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): CoroutineScope {
        return CoroutineScope(ioDispatcher + SupervisorJob())
    }

    @AppPrefs
    @Singleton
    @Provides
    fun provideAppPrefsDataStore(
        @ApplicationContext appContext: Context,
        @DataStoreCoroutineScope coroutineScope: CoroutineScope
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            scope = coroutineScope,
            produceFile = { appContext.preferencesDataStoreFile(AppDataStore.APP_DATASTORE_KEY) }
        )
    }

    @Singleton
    @Provides
    fun provideAppDataStore(appDataStoreImpl: AppDataStoreImpl): AppDataStore {
        return appDataStoreImpl
    }
}