package com.solo.mymovies.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.solo.mymovies.data.CustomResult
import com.solo.mymovies.data.remote.api.ApiService
import com.solo.mymovies.data.remote.paging.PopularMoviesPagingSource
import com.solo.mymovies.data.toMediaItem
import com.solo.mymovies.data.toMovieCrew
import com.solo.mymovies.data.toMovieDetails
import com.solo.mymovies.domain.model.ContentType
import com.solo.mymovies.domain.model.MediaItem
import com.solo.mymovies.domain.model.MovieCrew
import com.solo.mymovies.domain.model.MovieDetails
import com.solo.mymovies.domain.model.PopularMovie
import com.solo.mymovies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
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

    override fun getMovieDetails(movieId: Int): Flow<CustomResult<MovieDetails>> = flow {
        try {
            val response = apiService.getMovieDetails(movieId = movieId)
            emit(CustomResult.Success(response.toMovieDetails()))
        } catch (e: IOException) {
            emit(CustomResult.Failure("Network error: ${e.localizedMessage}"))
        } catch (e: HttpException) {
            emit(CustomResult.Failure("HTTP error: ${e.localizedMessage}"))
        } catch (e: Exception) {
            emit(CustomResult.Failure("Unexpected error: ${e.localizedMessage}"))
        }
    }

    override fun getMovieCredits(movieId: Int): Flow<CustomResult<List<MovieCrew>>> = flow {
        try {
            val response = apiService.getMovieCredits(movieId = movieId)
            emit(CustomResult.Success(
                response.crew
                    .filter { it.job == "Story" || it.department == "Writing" }
                    .map { it.toMovieCrew() }
            ))
        } catch (e: IOException) {
            emit(CustomResult.Failure("Network error: ${e.localizedMessage}"))
        } catch (e: HttpException) {
            emit(CustomResult.Failure("HTTP error: ${e.localizedMessage}"))
        } catch (e: Exception) {
            emit(CustomResult.Failure("Unexpected error: ${e.localizedMessage}"))
        }
    }

    override fun searchMediaByName(
        name: String,
        type: ContentType
    ): Flow<CustomResult<List<MediaItem>>> = flow {
        try {
            val response = when (type) {
                ContentType.MOVIE -> {
                    Timber.d("Searching movies")
                    apiService.searchMoviesByName(query = name)
                }

                ContentType.TV -> {
                    Timber.d("Searching tv shows")
                    apiService.searchTvShowsByName(query = name)
                }
            }
            emit(CustomResult.Success(
                response.searchedResult.map {
                    it.toMediaItem()
                }
            ))
        } catch (e: IOException) {
            emit(CustomResult.Failure("Network error: ${e.localizedMessage}"))
        } catch (e: HttpException) {
            emit(CustomResult.Failure("HTTP error: ${e.localizedMessage}"))
        } catch (e: Exception) {
            emit(CustomResult.Failure("Unexpected error: ${e.localizedMessage}"))
        }
    }
}