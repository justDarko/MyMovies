package com.solo.mymovies.domain.repository

import androidx.paging.PagingData
import com.solo.mymovies.data.CustomResult
import com.solo.mymovies.domain.model.ContentType
import com.solo.mymovies.domain.model.MediaItem
import com.solo.mymovies.domain.model.MovieCrew
import com.solo.mymovies.domain.model.MovieDetails
import com.solo.mymovies.domain.model.PopularMovie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(): Flow<PagingData<PopularMovie>>
    fun getMovieDetails(movieId: Int): Flow<CustomResult<MovieDetails>>
    fun getMovieCredits(movieId: Int): Flow<CustomResult<List<MovieCrew>>>
    fun searchMediaByName(name: String, type: ContentType): Flow<CustomResult<List<MediaItem>>>
}