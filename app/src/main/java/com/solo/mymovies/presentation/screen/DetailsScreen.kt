package com.solo.mymovies.presentation.screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.solo.mymovies.presentation.MovieViewModel
import timber.log.Timber

@Composable
fun DetailsScreen(
    movieId: Int,
    viewModel: MovieViewModel = hiltViewModel()
) {
    Timber.d("The movie id is: $movieId")
}