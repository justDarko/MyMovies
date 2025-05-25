package com.solo.mymovies.data.remote.api

import com.solo.mymovies.data.MOVIE
import com.solo.mymovies.data.SERIES
import com.solo.mymovies.data.remote.dto.MovieDetailsDTO
import com.solo.mymovies.data.remote.responseModels.MovieCreditsResponse
import com.solo.mymovies.data.remote.responseModels.PopularMoviesResponse
import com.solo.mymovies.data.remote.responseModels.SearchedMediaResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("${MOVIE}/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): PopularMoviesResponse

    @GET("${MOVIE}/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
    ): MovieDetailsDTO

    @GET("${MOVIE}/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int,
    ): MovieCreditsResponse

    @GET("search/${MOVIE}")
    suspend fun searchMoviesByName(
        @Query("query") query: String,
        @Query("page") page: Int = 1
    ): SearchedMediaResponse

    @GET("search/${SERIES}")
    suspend fun searchTvShowsByName(
        @Query("query") query: String,
        @Query("page") page: Int = 1
    ): SearchedMediaResponse
}