package com.solo.mymovies.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.solo.mymovies.data.remote.api.ApiService
import com.solo.mymovies.data.remote.paging.PopularMoviesPagingSource
import com.solo.mymovies.domain.model.PopularMovie
import com.solo.mymovies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MovieRepository {

    override fun getPopularMovies(): Flow<PagingData<PopularMovie>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { PopularMoviesPagingSource(apiService) }
        ).flow
    }
}