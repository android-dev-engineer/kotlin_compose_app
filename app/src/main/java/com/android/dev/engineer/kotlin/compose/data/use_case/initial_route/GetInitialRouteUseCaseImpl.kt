package com.android.dev.engineer.kotlin.compose.data.use_case.initial_route

import com.android.dev.engineer.kotlin.compose.data.di.IoDispatcher
import com.android.dev.engineer.kotlin.compose.data.domain.local.MainNavGraph
import com.android.dev.engineer.kotlin.compose.data.datastore.app.AppDataStore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetInitialRouteUseCaseImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val appDataStore: AppDataStore
) : GetInitialRouteUseCase {
    override suspend fun invoke(): MainNavGraph = withContext(dispatcher) {
        if (appDataStore.isIntroPending()) {
            MainNavGraph.Intro
        } else {
            MainNavGraph.SignIn
        }
    }
}