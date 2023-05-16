package com.android.dev.engineer.kotlin.compose.feature.upcoming_movies

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.*
import com.android.dev.engineer.kotlin.compose.data.domain.local.MovieItem
import kotlinx.coroutines.flow.flowOf

// https://developer.android.com/reference/kotlin/androidx/paging/compose/package-summary#(kotlinx.coroutines.flow.Flow).collectAsLazyPagingItems(kotlin.coroutines.CoroutineContext)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieListComposable(
    pagingItems: LazyPagingItems<MovieItem>,
    columnsSize: Int,
    onClickMovie: (MovieItem) -> Unit
) {
    var refreshing by rememberSaveable { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = refreshing,
        onRefresh = {
            pagingItems.refresh()
        }
    )
    if (pagingItems.loadState.refresh == LoadState.Loading && pagingItems.itemCount == 0) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        refreshing = pagingItems.loadState.refresh == LoadState.Loading && pagingItems.itemCount > 0
    }

    Box(
        Modifier.pullRefresh(pullRefreshState)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = columnsSize),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                count = pagingItems.itemCount,
                itemContent = { index ->
                    val movieItem = pagingItems[index]
                    if (movieItem != null) {
                        MovieItemComposable(
                            movieItem = movieItem,
                            onClickMovie = { onClickMovie(movieItem) }
                        )
                    }
                }
            )
            if (pagingItems.loadState.append == LoadState.Loading) {
                item(
                    span = { GridItemSpan(currentLineSpan = columnsSize) },
                    content = {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .padding(all = 8.dp)
                                .fillMaxWidth()
                                .wrapContentWidth(align = Alignment.CenterHorizontally)
                        )
                    }
                )
            }
        }

        PullRefreshIndicator(
            modifier = Modifier.align(Alignment.TopCenter),
            refreshing = refreshing,
            state = pullRefreshState
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMovieListComposable() {
    MovieListComposable(
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
        ).collectAsLazyPagingItems(),
        columnsSize = 3,
        onClickMovie = {}
    )
}