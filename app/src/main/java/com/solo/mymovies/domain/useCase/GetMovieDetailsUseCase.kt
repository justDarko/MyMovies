package com.solo.mymovies.domain.useCase

import com.solo.mymovies.data.CustomResult
import com.solo.mymovies.domain.model.MovieDetails
import com.solo.mymovies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseUseCase<GetMovieDetailsUseCase.Params, MovieDetails>() {

    override suspend fun execute(params: Params): Flow<CustomResult<MovieDetails>> {
        return movieRepository.getMovieDetails(movieId = params.movieId)
    }

    data class Params(
        val movieId: Int
    )
}