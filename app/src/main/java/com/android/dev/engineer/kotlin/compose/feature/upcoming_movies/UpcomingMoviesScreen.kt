package com.android.dev.engineer.kotlin.compose.feature.upcoming_movies

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.dev.engineer.kotlin.compose.data.domain.local.MovieItem
import com.android.dev.engineer.kotlin.compose.ui.theme.KotlinComposeAppTheme
import com.android.dev.engineer.kotlin.compose.util.ExcludeFromJacocoGeneratedReport
import kotlinx.coroutines.flow.flowOf

private const val COLUMN_SIZE = 3

@Composable
fun UpcomingMoviesScreen(
    viewModel: UpcomingMoviesViewModel = hiltViewModel()
) {
    val lazyPagingItems = viewModel.stateFlow.collectAsLazyPagingItems()
    UpcomingMoviesScreenComposable(lazyPagingItems)
}

@Composable
fun UpcomingMoviesScreenComposable(pagingItems: LazyPagingItems<MovieItem>) {
    KotlinComposeAppTheme {
        MovieListComposable(
            pagingItems = pagingItems,
            columnsSize = COLUMN_SIZE,
            onClickMovie = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
@ExcludeFromJacocoGeneratedReport
private fun PreviewSignInScreenComposable() {
    UpcomingMoviesScreenComposable(
        pagingItems = flowOf(
            PagingData.from(
                data = listOf(
                    MovieItem(
                        id = 1,
                        originalTitle = "Original title",
                        overview = "Overview",
                        popularity = 0.661,
                        posterPath = "/r16LpvYoE6ADjbG",
                        releaseDate = "2016-21-03",
                        title = "Title",
                        voteAverage = 8.5,
                        voteCount = 150
                    )
                )
            )
        ).collectAsLazyPagingItems()
    )
}