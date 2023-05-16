package com.android.dev.engineer.kotlin.compose.feature.upcoming_movies

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.android.dev.engineer.kotlin.compose.R
import com.android.dev.engineer.kotlin.compose.data.domain.local.MovieItem

private const val MOVIE_POSTER_RATIO = 2f / 3f

@Composable
fun MovieItemComposable(
    movieItem: MovieItem,
    onClickMovie: () -> Unit
) {
    Card(
        modifier = Modifier
            .aspectRatio(ratio = MOVIE_POSTER_RATIO)
            .clip(shape = RoundedCornerShape(size = 8.dp))
            .clickable { onClickMovie() }
    ) {
        Box {
            AsyncImage(
                contentScale = ContentScale.FillBounds,
                model = movieItem.posterPath,
                contentDescription = movieItem.originalTitle
            )
            Text(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .background(color = colorResource(id = R.color.black))
                    .clip(shape = CircleShape),
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.h5.fontSize,
                color = colorResource(id = R.color.white),
                text = movieItem.voteAverage.toString()
            )
            Column(
                modifier = Modifier
                    .background(color = colorResource(id = R.color.black).copy(alpha = 0.6f))
                    .padding(all = 8.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = movieItem.originalTitle,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.white),
                    fontSize = MaterialTheme.typography.body1.fontSize
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = movieItem.releaseDate,
                    color = colorResource(id = R.color.white),
                    fontSize = MaterialTheme.typography.body2.fontSize
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
            originalTitle = "Transformers: Rise of the Beasts",
            overview = "Overview",
            popularity = 0.661,
            posterPath = "/r16LpvYoE6ADjbG",
            releaseDate = "2022-01-12",
            title = "Transformers: Rise of the Beasts",
            voteAverage = 9.0,
            voteCount = 150
        ),
        onClickMovie = {}
    )
}