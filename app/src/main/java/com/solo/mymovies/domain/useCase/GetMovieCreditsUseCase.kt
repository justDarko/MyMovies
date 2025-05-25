package com.solo.mymovies.domain.useCase

import com.solo.mymovies.data.CustomResult
import com.solo.mymovies.domain.model.MovieCrew
import com.solo.mymovies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieCreditsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseUseCase<GetMovieCreditsUseCase.Params, List<MovieCrew>>() {

    override suspend fun execute(params: Params): Flow<CustomResult<List<MovieCrew>>> {
        return movieRepository.getMovieCredits(movieId = params.movieId)
    }

    data class Params(
        val movieId: Int
    )
}