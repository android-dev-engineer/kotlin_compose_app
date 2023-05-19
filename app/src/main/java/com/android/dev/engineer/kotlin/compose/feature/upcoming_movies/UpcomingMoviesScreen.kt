package com.android.dev.engineer.kotlin.compose.feature.upcoming_movies

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.dev.engineer.kotlin.compose.data.domain.local.MovieItem
import com.android.dev.engineer.kotlin.compose.ui.composable.ButtonComposable
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
        lazyPagingItems = lazyPagingItems,
        columnsSize = when (LocalConfiguration.current.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> COLUMN_SIZE_IN_PORTRAIT_MODE
            else -> COLUMN_SIZE_IN_LANDSCAPE_MODE
        },
        onClickMovie = onClickMovie
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UpcomingMoviesScreenComposable(
    lazyPagingItems: LazyPagingItems<MovieItem>,
    columnsSize: Int,
    onClickMovie: (MovieItem) -> Unit
) {
    KotlinComposeAppTheme {
        val isRefreshing by remember {
            derivedStateOf { lazyPagingItems.loadState.refresh == LoadState.Loading && lazyPagingItems.itemCount > 0 }
        }
        val pullRefreshState = rememberPullRefreshState(
            refreshing = isRefreshing,
            onRefresh = { lazyPagingItems.refresh() }
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState)
        ) {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(count = columnsSize),
                contentPadding = PaddingValues(all = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                content = {
                    items(
                        count = lazyPagingItems.itemCount,
                        itemContent = { index ->
                            val movieItem = lazyPagingItems[index]
                            if (movieItem != null) {
                                UpcomingMovieComposable(
                                    movieItem = movieItem,
                                    onClickMovie = { onClickMovie(movieItem) }
                                )
                            }
                        }
                    )

                    when (val state = lazyPagingItems.loadState.append) {
                        is LoadState.Loading -> {
                            item(
                                span = { GridItemSpan(currentLineSpan = columnsSize) },
                                content = {
                                    CircularProgressIndicator(
                                        modifier = Modifier
                                            .padding(all = 8.dp)
                                            .wrapContentWidth(align = Alignment.CenterHorizontally)
                                    )
                                }
                            )
                        }
                        is LoadState.NotLoading -> if (lazyPagingItems.itemCount > 0 && state.endOfPaginationReached) {
                            item(
                                span = { GridItemSpan(currentLineSpan = columnsSize) },
                                content = {
                                    Text(
                                        modifier = Modifier
                                            .padding(all = 8.dp)
                                            .wrapContentWidth(align = Alignment.CenterHorizontally),
                                        text = "That's enough scrolling for now :)",
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colors.primary
                                    )
                                }
                            )
                        }
                        is LoadState.Error -> {
                            item(
                                span = { GridItemSpan(currentLineSpan = columnsSize) },
                                content = {
                                    ButtonComposable(
                                        modifier = Modifier.wrapContentWidth(align = Alignment.CenterHorizontally),
                                        text = "Try again",
                                        onClick = {
                                            lazyPagingItems.retry()
                                        }
                                    )
                                }
                            )
                        }
                    }
                }
            )

            when (lazyPagingItems.loadState.refresh) {
                is LoadState.Loading -> if (lazyPagingItems.itemCount == 0) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is LoadState.NotLoading -> if (lazyPagingItems.itemCount == 0) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "No upcoming videos found"
                    )
                }
                is LoadState.Error -> {
                    ButtonComposable(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Try again",
                        onClick = {
                            lazyPagingItems.retry()
                        }
                    )
                }
            }

            PullRefreshIndicator(
                modifier = Modifier.align(Alignment.TopCenter),
                contentColor = MaterialTheme.colors.primary,
                refreshing = isRefreshing,
                state = pullRefreshState
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
@ExcludeFromJacocoGeneratedReport
private fun PreviewUpcomingMoviesScreenComposable() {
    UpcomingMoviesScreenComposable(
        lazyPagingItems = flowOf(PagingData.empty<MovieItem>()).collectAsLazyPagingItems(),
        columnsSize = COLUMN_SIZE_IN_PORTRAIT_MODE,
        onClickMovie = {}
    )
}