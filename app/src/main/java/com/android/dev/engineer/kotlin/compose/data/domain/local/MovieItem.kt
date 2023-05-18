package com.android.dev.engineer.kotlin.compose.data.domain.local

import com.android.dev.engineer.kotlin.compose.data.api.TheMovieApi.Companion.IMAGE_URL
import com.android.dev.engineer.kotlin.compose.data.domain.network.Movie
import com.android.dev.engineer.kotlin.compose.extension.toDateFormatted

data class MovieItem(
    val id: Int,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
)

fun Movie.toMovieItem() = MovieItem(
    id = id,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath?.let { IMAGE_URL + it }.orEmpty(),
    releaseDate = releaseDate.toDateFormatted(),
    title = title,
    voteAverage = voteAverage,
    voteCount = voteCount
)