package com.solo.mymovies.domain.model

data class MediaItem(
    val id: Int,
    val title: String?,
    val posterPath: String?,
    val name: String?,
    val voteAverage: Double
)
