package com.android.dev.engineer.kotlin.compose.feature.upcoming_movies

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.dev.engineer.kotlin.compose.data.domain.local.MovieItem
import com.android.dev.engineer.kotlin.compose.ui.composable.ButtonComposable
import com.android.dev.engineer.kotlin.compose.util.ExcludeFromJacocoGeneratedReport
import kotlinx.coroutines.flow.flowOf

// https://developer.android.com/reference/kotlin/androidx/paging/compose/package-summary#(kotlinx.coroutines.flow.Flow).collectAsLazyPagingItems(kotlin.coroutines.CoroutineContext)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieListComposable(
    lazyPagingItems: LazyPagingItems<MovieItem>,
    columnsSize: Int,
    onClickMovie: (MovieItem) -> Unit
) {
    val isRefreshing by remember {
        derivedStateOf { lazyPagingItems.loadState.refresh == LoadState.Loading && lazyPagingItems.itemCount > 0 }
    }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            lazyPagingItems.refresh()
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
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

        LazyVerticalGrid(
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
                            MovieItemComposable(
                                movieItem = movieItem,
                                onClickMovie = { onClickMovie(movieItem) }
                            )
                        }
                    }
                )
                if (lazyPagingItems.loadState.append == LoadState.Loading) {
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
                } else if (lazyPagingItems.loadState.append is LoadState.Error) {
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
        )

        PullRefreshIndicator(
            modifier = Modifier.align(Alignment.TopCenter),
            contentColor = MaterialTheme.colors.primary,
            refreshing = isRefreshing,
            state = pullRefreshState
        )
    }
}

@Preview(showBackground = true)
@Composable
@ExcludeFromJacocoGeneratedReport
private fun PreviewMovieListComposable() {
    MovieListComposable(
        lazyPagingItems = flowOf(PagingData.empty<MovieItem>()).collectAsLazyPagingItems(),
        columnsSize = 3,
        onClickMovie = {}
    )
}