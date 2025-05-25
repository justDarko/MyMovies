package com.solo.mymovies.data.remote.api

import com.solo.mymovies.data.remote.responseModels.PopularMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): PopularMoviesResponse

//    @GET("movie/{movie_id}")
//    suspend fun getMovieDetails(
//        @Path("movie_id") movieId: Int,
//        @Query("api_key") apiKey: String
//    ): MovieDetailDto

//    @GET("search/movie")
//    suspend fun searchMovies(
//        @Query("api_key") apiKey: String,
//        @Query("query") query: String,
//        @Query("page") page: Int
//    ): MovieResponseDto
}