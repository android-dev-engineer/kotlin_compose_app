package com.android.dev.engineer.kotlin.compose.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.os.bundleOf
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.android.dev.engineer.kotlin.compose.data.domain.local.MainNavGraph
import com.android.dev.engineer.kotlin.compose.feature.intro.IntroScreen
import com.android.dev.engineer.kotlin.compose.feature.movie.MovieScreen
import com.android.dev.engineer.kotlin.compose.feature.movie.MovieViewModel.Companion.ID_ARG
import com.android.dev.engineer.kotlin.compose.feature.sign_in.SignInScreen
import com.android.dev.engineer.kotlin.compose.feature.upcoming_movies.UpcomingMoviesScreen
import dagger.hilt.android.AndroidEntryPoint

// Hilt gradle plugin causes Jacoco coverage issue: https://github.com/google/dagger/issues/1982
@AndroidEntryPoint(ComponentActivity::class)
class MainActivity : Hilt_MainActivity() {
    companion object {
        private const val START_DESTINATION_KEY = "start_destination_key"

        fun prepareBundle(startDestination: String): Bundle {
            return bundleOf(START_DESTINATION_KEY to startDestination)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainNavHost(
                startDestination = requireNotNull(intent.extras?.getString(START_DESTINATION_KEY))
            )
        }
    }
}

@Composable
fun MainNavHost(startDestination: String) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = MainNavGraph.Intro.route) {
            IntroScreen(
                onSkipClicked = { mainNavGraph ->
                    navController.navigate(mainNavGraph.route) {
                        popUpTo(route = MainNavGraph.Intro.route) { inclusive = true }
                    }
                }
            )
        }
        composable(MainNavGraph.SignIn.route) {
            SignInScreen(
                onLoggedIn = { mainNavGraph ->
                    navController.navigate(mainNavGraph.route) {
                        popUpTo(route = MainNavGraph.SignIn.route) { inclusive = true }
                    }
                }
            )
        }
        composable(MainNavGraph.UpcomingMovies.route) {
            UpcomingMoviesScreen(
                onClickMovie = { movieItem ->
                    navController.navigate(route = MainNavGraph.Movie.route + "/${movieItem.id}")
                }
            )
        }
        composable(
            route = MainNavGraph.Movie.route + "/{$ID_ARG}",
            arguments = listOf(
                navArgument(name = ID_ARG, builder = { type = NavType.IntType })
            )
        ) {
            MovieScreen()
        }
    }
}