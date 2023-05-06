package com.android.dev.engineer.kotlin.compose.data.api.movie

import com.android.dev.engineer.kotlin.compose.data.api.TheMovieApi
import com.android.dev.engineer.kotlin.compose.data.domain.network.UpcomingMovies
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val theMovieApi: TheMovieApi
) : MovieRepository {
    override suspend fun getUpcomingMovies(page: Int): UpcomingMovies {
        return theMovieApi.getUpcomingMovies(page)
    }
}