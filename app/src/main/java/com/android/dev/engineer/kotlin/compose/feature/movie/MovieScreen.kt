package com.android.dev.engineer.kotlin.compose.feature.movie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.dev.engineer.kotlin.compose.ui.theme.KotlinComposeAppTheme
import com.android.dev.engineer.kotlin.compose.util.ExcludeFromJacocoGeneratedReport

@Composable
fun MovieScreen(
    movieViewModel: MovieViewModel = hiltViewModel()
) {
    MovieScreenComposable(movieViewModel.movieId)
}

@Composable
fun MovieScreenComposable(movieId: Int) {
    KotlinComposeAppTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Movie details of movie: $movieId"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
@ExcludeFromJacocoGeneratedReport
private fun PreviewMovieScreenComposable() {
    MovieScreenComposable(
        movieId = 1234
    )
}