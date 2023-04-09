package com.android.dev.engineer.kotlin.compose.fake

import com.android.dev.engineer.kotlin.compose.data.local.AppDataStore

class AppDataStoreFake : AppDataStore {
    var markIntroComplete: Boolean = false
    var isIntroPending = true

    override suspend fun isIntroPending(): Boolean {
        return isIntroPending
    }

    override suspend fun markIntroComplete() {
        markIntroComplete = true
    }
}