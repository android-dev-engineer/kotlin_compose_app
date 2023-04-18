package com.android.dev.engineer.kotlin.compose.data.datastore.app

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.android.dev.engineer.kotlin.compose.data.di.AppPrefs
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppDataStoreImpl @Inject constructor(
    @AppPrefs private val dataStore: DataStore<Preferences>
) : AppDataStore {
    companion object {
        private const val APP_INTRO_KEY = "app_intro_key"
    }

    private val introKey by lazy { booleanPreferencesKey(name = APP_INTRO_KEY) }

    override suspend fun isIntroPending(): Boolean {
        return dataStore.data.map { preferences ->
            preferences[introKey] ?: true
        }.first()
    }

    override suspend fun markIntroComplete() {
        dataStore.edit { preferences ->
            preferences[introKey] = false
        }
    }
}