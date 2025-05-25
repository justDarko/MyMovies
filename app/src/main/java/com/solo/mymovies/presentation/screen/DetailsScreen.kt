package com.solo.mymovies.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.solo.mymovies.presentation.MovieViewModel

@Composable
fun DetailsScreen(
    movieId: Int,
    viewModel: MovieViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsState()

    LaunchedEffect(movieId) {
        viewModel.getMovieDetails(movieId = movieId)
        viewModel.getMovieCredits(movieId = movieId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        viewState.error?.let { errorMsg ->
            ErrorSection(
                message = errorMsg,
                onRetry = {
                    viewModel.apply {
                        getMovieDetails(movieId = movieId)
                        getMovieCredits(movieId = movieId)
                    }
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Show movie details content if available
        viewState.movieDetails?.let { movie ->
            Text(
                text = movie.title,
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(8.dp))

            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Rating: ${movie.voteAverage}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Votes: ${movie.voteCount}",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Release Date: ${movie.releaseDate}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Genres: " + movie.genres.joinToString(", ") { it.name },
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Show authors if available
            viewState.movieAuthors?.takeIf { it.isNotEmpty() }?.let { authors ->
                Text(
                    text = "Authors: ${authors.joinToString(", ") { it.name }}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = movie.overview ?: "No description available.",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun ErrorSection(message: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message, color = Color.Red)
        Button(onClick = onRetry) {
            Text("Retry")
        }
    }
}