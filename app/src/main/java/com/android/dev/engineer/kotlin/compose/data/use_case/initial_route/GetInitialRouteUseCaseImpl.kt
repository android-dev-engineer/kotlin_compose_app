package com.android.dev.engineer.kotlin.compose.data.use_case.initial_route

import com.android.dev.engineer.kotlin.compose.data.di.IoDispatcher
import com.android.dev.engineer.kotlin.compose.data.domain.MainNavGraph
import com.android.dev.engineer.kotlin.compose.data.local.AppDataStore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetInitialRouteUseCaseImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val appDataStore: AppDataStore
) : GetInitialRouteUseCase {
    override suspend fun invoke(): MainNavGraph = withContext(ioDispatcher) {
        if (appDataStore.isIntroPending()) {
            MainNavGraph.Intro
        } else {
            MainNavGraph.SignIn
        }
    }
}