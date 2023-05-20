package com.android.dev.engineer.kotlin.compose.data.domain.local

sealed class MainNavGraph(val route: String) {
    object Intro : MainNavGraph(route = "intro")
    object SignIn : MainNavGraph(route = "sign_in")
    object UpcomingMovies : MainNavGraph(route = "upcoming_movies")
    object Movie : MainNavGraph(route = "movie")
}