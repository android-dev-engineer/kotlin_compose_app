package com.android.dev.engineer.kotlin.compose.feature.upcoming_movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.android.dev.engineer.kotlin.compose.data.api.TheMovieApi.Companion.DEFAULT_PAGE_SIZE
import com.android.dev.engineer.kotlin.compose.data.domain.local.MovieItem
import com.android.dev.engineer.kotlin.compose.data.domain.local.toMovieItem
import com.android.dev.engineer.kotlin.compose.data.use_case.upcoming_movie.GetUpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class UpcomingMoviesViewModel @Inject constructor(
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase
) : ViewModel() {
    val stateFlow: Flow<PagingData<MovieItem>> by lazy { getPopularMoviePageSource() }

    private fun getPopularMoviePageSource(): Flow<PagingData<MovieItem>> {
        return Pager(
            pagingSourceFactory = {
                UpcomingMoviesPagingSource(
                    getUpcomingMoviesUseCase = getUpcomingMoviesUseCase
                )
            },
            config = PagingConfig(
                pageSize = DEFAULT_PAGE_SIZE
            )
        ).flow.cachedIn(viewModelScope).map { pagingData ->
            pagingData.map { movie -> movie.toMovieItem() }
        }
    }
}