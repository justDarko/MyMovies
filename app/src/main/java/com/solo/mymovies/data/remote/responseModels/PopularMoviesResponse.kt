package com.solo.mymovies.data.remote.responseModels

import com.google.gson.annotations.SerializedName
import com.solo.mymovies.data.remote.dto.PopularMovieDTO

data class PopularMoviesResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val movies: List<PopularMovieDTO>
)
