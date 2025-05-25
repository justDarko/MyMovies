package com.solo.mymovies.domain.useCase

import androidx.paging.PagingData
import com.solo.mymovies.domain.model.PopularMovie
import com.solo.mymovies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Flow<PagingData<PopularMovie>> {
        return movieRepository.getPopularMovies()
    }
}