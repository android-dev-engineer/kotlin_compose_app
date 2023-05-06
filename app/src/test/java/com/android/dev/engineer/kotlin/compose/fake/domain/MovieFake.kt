package com.android.dev.engineer.kotlin.compose.fake.domain

import com.android.dev.engineer.kotlin.compose.data.domain.network.Movie
import com.android.dev.engineer.kotlin.compose.data.domain.network.UpcomingMovies

object MovieFake {
    private fun createMovie() = Movie(
        id = 1,
        originalTitle = "Original title",
        overview = "Overview",
        popularity = 0.661,
        posterPath = "/r16LpvYoE6ADjbG",
        releaseDate = "2016-21-03",
        title = "",
        voteAverage = 8.5,
        voteCount = 150
    )

    fun createUpcomingMovies(
        page: Int = 1,
        totalPages: Int = 10
    ) = UpcomingMovies(
        page = page,
        movies = listOf(
            createMovie()
        ),
        totalPages = totalPages
    )
}