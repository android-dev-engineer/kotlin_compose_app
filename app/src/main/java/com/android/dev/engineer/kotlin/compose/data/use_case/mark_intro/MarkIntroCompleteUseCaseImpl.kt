package com.android.dev.engineer.kotlin.compose.data.use_case.mark_intro

import com.android.dev.engineer.kotlin.compose.data.datastore.app.AppDataStore
import java.io.IOException
import javax.inject.Inject

class MarkIntroCompleteUseCaseImpl @Inject constructor(
    private val appDataStore: AppDataStore
) : MarkIntroCompleteUseCase {
    @Throws(IOException::class)
    override suspend fun invoke() {
        appDataStore.markIntroComplete()
    }
}