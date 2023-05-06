package com.android.dev.engineer.kotlin.compose.data.api

import com.android.dev.engineer.kotlin.compose.data.api.authentication.AuthenticationApi
import com.android.dev.engineer.kotlin.compose.data.api.movie.MovieApi

interface TheMovieApi : AuthenticationApi, MovieApi {
    companion object {
        const val BASE_API_URL = "https://api.themoviedb.org/3/"
        const val DEFAULT_PAGE_SIZE = 20
    }
}