package com.solo.mymovies.domain.repository

import androidx.paging.PagingData
import com.solo.mymovies.domain.model.PopularMovie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(): Flow<PagingData<PopularMovie>>
}