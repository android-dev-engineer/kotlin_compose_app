package com.android.dev.engineer.kotlin.compose.data.local

interface AppDataStore {
    suspend fun markIntroComplete()
    suspend fun isIntroPending(): Boolean

    companion object {
        const val APP_DATASTORE_KEY = "app_datastore_key"
    }
}