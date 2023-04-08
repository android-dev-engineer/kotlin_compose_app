package com.android.dev.engineer.kotlin.compose.composable.fake

import com.android.dev.engineer.kotlin.compose.data.local.AppDataStore

class AppDataStoreFake : AppDataStore {
    var markIntroComplete: Boolean = false
    var isIntroPending = false

    override suspend fun markIntroComplete() {
        markIntroComplete = true
    }

    override suspend fun isIntroPending(): Boolean {
        return isIntroPending
    }
}