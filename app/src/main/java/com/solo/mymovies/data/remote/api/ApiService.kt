package com.solo.mymovies.data.remote.api

import com.solo.mymovies.data.MOVIE
import com.solo.mymovies.data.remote.dto.MovieDetailsDTO
import com.solo.mymovies.data.remote.responseModels.MovieCreditsResponse
import com.solo.mymovies.data.remote.responseModels.PopularMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("${MOVIE}popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): PopularMoviesResponse

    @GET("${MOVIE}{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
    ): MovieDetailsDTO

    @GET("${MOVIE}{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int,
    ): MovieCreditsResponse

//    @GET("search/movie")
//    suspend fun searchMovies(
//        @Query("api_key") apiKey: String,
//        @Query("query") query: String,
//        @Query("page") page: Int
//    ): MovieResponseDto
}