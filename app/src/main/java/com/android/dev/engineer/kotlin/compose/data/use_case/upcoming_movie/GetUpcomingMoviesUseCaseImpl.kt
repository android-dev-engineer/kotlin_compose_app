package com.android.dev.engineer.kotlin.compose.data.use_case.upcoming_movie

import com.android.dev.engineer.kotlin.compose.data.api.movie.MovieRepository
import com.android.dev.engineer.kotlin.compose.data.domain.network.UpcomingMovies
import javax.inject.Inject

class GetUpcomingMoviesUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : GetUpcomingMoviesUseCase {
    override suspend fun invoke(page: Int): UpcomingMovies {
        return movieRepository.getUpcomingMovies(page)
    }
}