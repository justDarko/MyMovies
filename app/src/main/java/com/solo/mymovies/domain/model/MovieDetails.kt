package com.solo.mymovies.domain.model

import com.google.gson.annotations.SerializedName
import com.solo.mymovies.data.remote.dto.GenreDTO

data class MovieDetails(
    val backdropPath: String?,
    val budget: Int,
    val genres: List<GenreDTO>,
    val homepage: String?,
    val id: Int,
    val originalTitle: String,
    val overview: String?,
    val popularity: Double,
    val posterPath: String?,
    val releaseDate: String,
    val status: String,
    val tagline: String?,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
)
