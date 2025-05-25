package com.solo.mymovies.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.solo.mymovies.data.remote.api.ApiService
import com.solo.mymovies.data.toPopularMovie
import com.solo.mymovies.domain.model.PopularMovie

class PopularMoviesPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, PopularMovie>() {
    override fun getRefreshKey(state: PagingState<Int, PopularMovie>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularMovie> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getPopularMovies(page)
            val movies = response.movies.map { it.toPopularMovie() }

            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (movies.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}