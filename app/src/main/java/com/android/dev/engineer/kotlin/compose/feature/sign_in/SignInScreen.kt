package com.android.dev.engineer.kotlin.compose.feature.sign_in

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.dev.engineer.kotlin.compose.data.domain.local.MainNavGraph
import com.android.dev.engineer.kotlin.compose.ui.composable.ButtonComposable
import com.android.dev.engineer.kotlin.compose.ui.theme.KotlinComposeAppTheme
import com.android.dev.engineer.kotlin.compose.util.ExcludeFromJacocoGeneratedReport

@Composable
fun SignInScreen(
    onLoggedIn: (MainNavGraph) -> Unit
) {
    SignInScreenComposable(
        onLoggedIn = onLoggedIn
    )
}

// TODO implement sign in screen
@Composable
fun SignInScreenComposable(
    onLoggedIn: (MainNavGraph) -> Unit
) {
    KotlinComposeAppTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ButtonComposable(
                text = "Go to upcoming movies",
                onClick = { onLoggedIn(MainNavGraph.UpcomingMovies) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
@ExcludeFromJacocoGeneratedReport
private fun PreviewSignInScreenComposable() {
    SignInScreenComposable(
        onLoggedIn = {}
    )
}