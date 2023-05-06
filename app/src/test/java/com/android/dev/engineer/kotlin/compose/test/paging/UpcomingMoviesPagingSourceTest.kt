package com.android.dev.engineer.kotlin.compose.test.paging

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.dev.engineer.kotlin.compose.data.api.TheMovieApi.Companion.DEFAULT_PAGE_SIZE
import com.android.dev.engineer.kotlin.compose.data.domain.network.Movie
import com.android.dev.engineer.kotlin.compose.fake.GetUpcomingMoviesUseCaseFake
import com.android.dev.engineer.kotlin.compose.fake.domain.MovieFake.createUpcomingMovies
import com.android.dev.engineer.kotlin.compose.feature.movie.UpcomingMoviesPagingSource
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.internal.EMPTY_RESPONSE
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

@ExperimentalCoroutinesApi
class UpcomingMoviesPagingSourceTest {
    private lateinit var getUpcomingMoviesUseCase: GetUpcomingMoviesUseCaseFake
    private lateinit var upcomingMoviesPagingSource: UpcomingMoviesPagingSource

    @Before
    fun setUp() {
        getUpcomingMoviesUseCase = GetUpcomingMoviesUseCaseFake()
        upcomingMoviesPagingSource = UpcomingMoviesPagingSource(getUpcomingMoviesUseCase = getUpcomingMoviesUseCase)
    }

    @Test
    fun `test initial page`() = runTest {
        getUpcomingMoviesUseCase.upcomingMovies = createUpcomingMovies(
            page = 1,
            totalPages = 10
        )

        val loadResult = upcomingMoviesPagingSource.load(
            params = PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = DEFAULT_PAGE_SIZE,
                placeholdersEnabled = true
            )
        ) as PagingSource.LoadResult.Page

        val expectedPage = PagingSource.LoadResult.Page(
            data = getUpcomingMoviesUseCase.upcomingMovies.movies,
            prevKey = null,
            nextKey = 2
        )
        assertEquals(expectedPage, loadResult)
    }

    @Test
    fun `test next page`() = runTest {
        getUpcomingMoviesUseCase.upcomingMovies = createUpcomingMovies(
            page = 2,
            totalPages = 10
        )

        val loadResult = upcomingMoviesPagingSource.load(
            params = PagingSource.LoadParams.Append(
                key = getUpcomingMoviesUseCase.upcomingMovies.page,
                loadSize = DEFAULT_PAGE_SIZE,
                placeholdersEnabled = true
            )
        ) as PagingSource.LoadResult.Page

        val expectedPage = PagingSource.LoadResult.Page(
            data = getUpcomingMoviesUseCase.upcomingMovies.movies,
            prevKey = null,
            nextKey = 3
        )
        assertEquals(expectedPage, loadResult)
    }

    @Test
    fun `test last page`() = runTest {
        getUpcomingMoviesUseCase.upcomingMovies = createUpcomingMovies(
            page = 10,
            totalPages = 10
        )
        val loadResult = upcomingMoviesPagingSource.load(
            params = PagingSource.LoadParams.Append(
                key = getUpcomingMoviesUseCase.upcomingMovies.page,
                loadSize = DEFAULT_PAGE_SIZE,
                placeholdersEnabled = true
            )
        ) as PagingSource.LoadResult.Page

        val expectedPage = PagingSource.LoadResult.Page(
            data = getUpcomingMoviesUseCase.upcomingMovies.movies,
            prevKey = null,
            nextKey = null
        )
        assertEquals(expectedPage, loadResult)
    }

    @Test
    fun `test fetch page with io exception error`() = runTest {
        getUpcomingMoviesUseCase.error = IOException()

        val loadResult = upcomingMoviesPagingSource.load(
            params = PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = DEFAULT_PAGE_SIZE,
                placeholdersEnabled = true
            )
        ) as PagingSource.LoadResult.Error

        assertEquals(getUpcomingMoviesUseCase.error, loadResult.throwable)
    }

    @Test
    fun `test fetch page with http exception error`() = runTest {
        getUpcomingMoviesUseCase.error = HttpException(Response.error<String>(500, EMPTY_RESPONSE))

        val loadResult = upcomingMoviesPagingSource.load(
            params = PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = DEFAULT_PAGE_SIZE,
                placeholdersEnabled = true
            )
        ) as PagingSource.LoadResult.Error

        assertEquals(getUpcomingMoviesUseCase.error, loadResult.throwable)
    }

    @Test
    fun `test refresh key`() {
        val initialPage = upcomingMoviesPagingSource.getRefreshKey(
            state = PagingState(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = DEFAULT_PAGE_SIZE),
                leadingPlaceholderCount = 0
            )
        )
        assertEquals(1, initialPage)
    }
}