package com.solo.mymovies.data.remote.responseModels

import com.google.gson.annotations.SerializedName
import com.solo.mymovies.data.remote.dto.MovieCrewDTO

data class MovieCreditsResponse(
    @SerializedName("crew")
    val crew: List<MovieCrewDTO>
)
