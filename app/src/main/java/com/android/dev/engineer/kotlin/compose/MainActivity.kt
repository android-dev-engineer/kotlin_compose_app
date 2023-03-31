package com.android.dev.engineer.kotlin.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.dev.engineer.kotlin.compose.data.domain.MainNavGraph
import com.android.dev.engineer.kotlin.compose.sign_in.SignInScreen
import com.android.dev.engineer.kotlin.compose.intro.IntroScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // TODO get start destination via bundle
            MainNavHost(startDestination = MainNavGraph.Intro.route)
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
                onSkipClicked = {
                    navController.navigate(it.route) {
                        popUpTo(route = MainNavGraph.Intro.route) { inclusive = true }
                    }
                }
            )
        }
        composable(MainNavGraph.SignIn.route) { SignInScreen() }
    }
}