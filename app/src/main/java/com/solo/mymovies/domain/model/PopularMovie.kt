package com.solo.mymovies.domain.model

data class PopularMovie(
    val backdropPath: String?,
    val genreIds: List<Int>?,
    val id: Int,
    val originalTitle: String?,
    val overview: String?,
    val posterPath: String?,
    val title: String?,
)
