package com.android.dev.engineer.kotlin.compose.data.api

import com.android.dev.engineer.kotlin.compose.data.api.authentication.AuthenticationApi

interface TheMovieApi : AuthenticationApi {
    companion object {
        const val BASE_API_URL = "https://api.themoviedb.org/3/"
    }
}