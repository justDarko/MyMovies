package com.solo.mymovies.data

import com.solo.mymovies.data.remote.dto.PopularMovieDTO
import com.solo.mymovies.domain.model.PopularMovie

fun PopularMovieDTO.toPopularMovie() = PopularMovie(
    id = id,
    overview = overview,
    backdropPath = backdropPath,
    originalTitle = originalTitle,
    title = title,
    genreIds = genreIds,
    posterPath = posterPath
)