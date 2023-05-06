package com.android.dev.engineer.kotlin.compose.data.use_case.upcoming_movie

import com.android.dev.engineer.kotlin.compose.data.domain.network.UpcomingMovies

interface GetUpcomingMoviesUseCase {
    suspend operator fun invoke(page: Int): UpcomingMovies
}