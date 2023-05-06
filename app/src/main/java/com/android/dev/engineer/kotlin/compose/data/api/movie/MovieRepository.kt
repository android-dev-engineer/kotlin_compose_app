package com.android.dev.engineer.kotlin.compose.data.api.movie

import com.android.dev.engineer.kotlin.compose.data.domain.network.UpcomingMovies

interface MovieRepository {
    suspend fun getUpcomingMovies(page: Int): UpcomingMovies
}