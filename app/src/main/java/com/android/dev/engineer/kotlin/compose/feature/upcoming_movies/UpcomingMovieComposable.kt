package com.android.dev.engineer.kotlin.compose.feature.upcoming_movies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.android.dev.engineer.kotlin.compose.data.domain.local.MovieItem

@Composable
fun MovieItemComposable(
    movieItem: MovieItem,
    onClickMovie: () -> Unit
) {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClickMovie() }
    ) {
        Row {
            AsyncImage(
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(100.dp)
                    .width(75.dp)
                    .align(CenterVertically),
                model = movieItem.posterPath,
                contentDescription = movieItem.originalTitle
            )
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = movieItem.originalTitle
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = movieItem.releaseDate
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = movieItem.voteAverage.toString()
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewMovieItemComposable() {
    MovieItemComposable(
        movieItem = MovieItem(
            id = 1,
            originalTitle = "Original title",
            overview = "Overview",
            popularity = 0.661,
            posterPath = "/r16LpvYoE6ADjbG",
            releaseDate = "2022-01-12",
            title = "Title",
            voteAverage = 9.0,
            voteCount = 150
        ),
        onClickMovie = {}
    )
}