package com.android.dev.engineer.kotlin.compose.data.use_case.initial_route

import com.android.dev.engineer.kotlin.compose.data.domain.local.MainNavGraph

interface GetInitialRouteUseCase {
    suspend operator fun invoke(): MainNavGraph
}