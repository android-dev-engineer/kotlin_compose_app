package com.android.dev.engineer.kotlin.compose.fake

import com.android.dev.engineer.kotlin.compose.data.domain.MainNavGraph
import com.android.dev.engineer.kotlin.compose.data.use_case.initial_route.GetInitialRouteUseCase

class GetInitialRouteUseCaseFake : GetInitialRouteUseCase {
    lateinit var mainNavGraph: MainNavGraph
    var exception: Exception? = null

    override suspend fun invoke(): MainNavGraph {
        throw checkNotNull(exception) {
            return mainNavGraph
        }
    }
}