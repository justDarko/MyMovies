package com.solo.mymovies.data.remote.responseModels

import com.google.gson.annotations.SerializedName
import com.solo.mymovies.data.remote.dto.MediaItemDTO

data class SearchedMediaResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val searchedResult: List<MediaItemDTO>
)
