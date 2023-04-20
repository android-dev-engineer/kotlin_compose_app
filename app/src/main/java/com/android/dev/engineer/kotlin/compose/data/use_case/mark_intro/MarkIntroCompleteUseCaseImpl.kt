package com.android.dev.engineer.kotlin.compose.data.use_case.mark_intro

import com.android.dev.engineer.kotlin.compose.data.di.IoDispatcher
import com.android.dev.engineer.kotlin.compose.data.datastore.app.AppDataStore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MarkIntroCompleteUseCaseImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val appDataStore: AppDataStore
) : MarkIntroCompleteUseCase {
    override suspend fun invoke() = withContext(dispatcher) {
        appDataStore.markIntroComplete()
    }
}