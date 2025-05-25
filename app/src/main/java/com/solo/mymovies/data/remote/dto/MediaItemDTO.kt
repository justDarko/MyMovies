package com.solo.mymovies.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MediaItemDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("vote_average")
    val voteAverage: Double,
)
