package com.android.dev.engineer.kotlin.compose.feature.upcoming_movies

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.dev.engineer.kotlin.compose.data.domain.local.MovieItem
import com.android.dev.engineer.kotlin.compose.ui.theme.KotlinComposeAppTheme
import com.android.dev.engineer.kotlin.compose.util.ExcludeFromJacocoGeneratedReport
import kotlinx.coroutines.flow.flowOf

private const val COLUMN_SIZE_IN_PORTRAIT_MODE = 3
private const val COLUMN_SIZE_IN_LANDSCAPE_MODE = 4

@Composable
fun UpcomingMoviesScreen(
    viewModel: UpcomingMoviesViewModel = hiltViewModel(),
    onClickMovie: (MovieItem) -> Unit
) {
    val lazyPagingItems = viewModel.stateFlow.collectAsLazyPagingItems()
    UpcomingMoviesScreenComposable(
        pagingItems = lazyPagingItems,
        onClickMovie = onClickMovie
    )
}

@Composable
fun UpcomingMoviesScreenComposable(
    pagingItems: LazyPagingItems<MovieItem>,
    onClickMovie: (MovieItem) -> Unit
) {
    KotlinComposeAppTheme {
        val configuration = LocalConfiguration.current
        MovieListComposable(
            lazyPagingItems = pagingItems,
            columnsSize = when (configuration.orientation) {
                Configuration.ORIENTATION_PORTRAIT -> COLUMN_SIZE_IN_PORTRAIT_MODE
                else -> COLUMN_SIZE_IN_LANDSCAPE_MODE
            },
            onClickMovie = onClickMovie
        )
    }
}

@Preview(showBackground = true)
@Composable
@ExcludeFromJacocoGeneratedReport
private fun PreviewSignInScreenComposable() {
    UpcomingMoviesScreenComposable(
        pagingItems = flowOf(PagingData.empty<MovieItem>()).collectAsLazyPagingItems(),
        onClickMovie = {}
    )
}