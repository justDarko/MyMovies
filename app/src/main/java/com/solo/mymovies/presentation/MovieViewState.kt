package com.solo.mymovies.presentation

import com.solo.mymovies.domain.model.MovieCrew
import com.solo.mymovies.domain.model.MovieDetails

data class MovieViewState(
    val movieDetails: MovieDetails? = null,
    val movieAuthors: List<MovieCrew>? = null
)
