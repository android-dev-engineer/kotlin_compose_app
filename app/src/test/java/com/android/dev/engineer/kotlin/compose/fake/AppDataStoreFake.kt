package com.android.dev.engineer.kotlin.compose.fake

import com.android.dev.engineer.kotlin.compose.data.datastore.app.AppDataStore

class AppDataStoreFake : AppDataStore {
    var isIntroPending = true

    override suspend fun isIntroPending(): Boolean {
        return isIntroPending
    }

    override suspend fun markIntroComplete() {
        isIntroPending = false
    }
}