package com.solo.mymovies.domain.useCase

import com.solo.mymovies.data.CustomResult
import com.solo.mymovies.domain.model.ContentType
import com.solo.mymovies.domain.model.MediaItem
import com.solo.mymovies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMediaUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseUseCase<SearchMediaUseCase.Params, List<MediaItem>>() {

    override suspend fun execute(params: Params): Flow<CustomResult<List<MediaItem>>> {
        return movieRepository.searchMediaByName(name = params.query, type = params.type)
    }

    data class Params(
        val query: String,
        val type: ContentType
    )
}