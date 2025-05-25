package com.solo.mymovies.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.solo.mymovies.domain.model.PopularMovie
import com.solo.mymovies.presentation.MovieViewModel

@Composable
fun HomeScreen(
    viewModel: MovieViewModel = hiltViewModel(),
    onNavigateToDetails: (movieId: Int) -> Unit
) {
    val movies = viewModel.popularMovies.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        items(movies.itemCount) { index ->
            val movie = movies[index]
            if (movie != null) {
                MovieItem(movie = movie, onClick = {
                    onNavigateToDetails(movie.id)
                })
            }
        }
    }
}

@Composable
fun MovieItem(movie: PopularMovie, onClick: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable { onClick() }) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
            contentDescription = movie.title,
            modifier = Modifier
                .size(100.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            movie.title?.let { title ->
                Text(
                    title,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            movie.overview?.let { description ->
                Text(
                    description,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 3
                )
            }
        }
    }
}