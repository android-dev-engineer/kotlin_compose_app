package com.android.dev.engineer.kotlin.compose.data.api.movie

import com.android.dev.engineer.kotlin.compose.data.domain.network.UpcomingMovies
import retrofit2.http.POST
import retrofit2.http.Query

interface MovieApi {
    @POST("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int
    ): UpcomingMovies
}