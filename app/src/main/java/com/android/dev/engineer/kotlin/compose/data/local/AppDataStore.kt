package com.android.dev.engineer.kotlin.compose.data.local

interface AppDataStore {
    suspend fun isIntroPending(): Boolean
    suspend fun markIntroComplete()

    companion object {
        const val APP_DATASTORE_KEY = "app_datastore_key"
    }
}