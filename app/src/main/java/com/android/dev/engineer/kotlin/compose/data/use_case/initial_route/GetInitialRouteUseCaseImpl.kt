package com.android.dev.engineer.kotlin.compose.data.use_case.initial_route

import com.android.dev.engineer.kotlin.compose.data.datastore.app.AppDataStore
import com.android.dev.engineer.kotlin.compose.data.domain.local.MainNavGraph
import javax.inject.Inject

class GetInitialRouteUseCaseImpl @Inject constructor(
    private val appDataStore: AppDataStore
) : GetInitialRouteUseCase {
    override suspend fun invoke(): MainNavGraph {
        return if (appDataStore.isIntroPending()) {
            MainNavGraph.Intro
        } else {
            MainNavGraph.SignIn
        }
    }
}