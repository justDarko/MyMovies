package com.solo.mymovies.presentation

import com.solo.mymovies.data.CustomResult
import com.solo.mymovies.domain.model.MovieDetails
import com.solo.mymovies.domain.useCase.GetMovieCreditsUseCase
import com.solo.mymovies.domain.useCase.GetMovieDetailsUseCase
import com.solo.mymovies.domain.useCase.GetPopularMoviesUseCase
import com.solo.mymovies.domain.useCase.SearchMediaUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieViewModelTest {

    private val dispatcher = StandardTestDispatcher()
    private val testScope = TestScope(dispatcher)

    // Mocks for all UseCases
    private val getPopularMoviesUseCase = mockk<GetPopularMoviesUseCase>(relaxed = true)
    private val getMovieDetailsUseCase = mockk<GetMovieDetailsUseCase>()
    private val getMovieCreditsUseCase = mockk<GetMovieCreditsUseCase>(relaxed = true)
    private val searchMediaUseCase = mockk<SearchMediaUseCase>(relaxed = true)

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = MovieViewModel(
            getPopularMoviesUseCase,
            getMovieDetailsUseCase,
            getMovieCreditsUseCase,
            searchMediaUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getMovieDetails updates state on success`() = testScope.runTest {
        // Given
        val movieId = 123
        val expectedDetails = MovieDetails(
            backdropPath = "/backdrop.jpg",
            budget = 1000000,
            genres = emptyList(), // or listOf(GenreDTO(...)) if needed
            homepage = "https://example.com",
            id = movieId,
            originalTitle = "Original Title",
            overview = "This is a test movie overview.",
            popularity = 8.5,
            posterPath = "/poster.jpg",
            releaseDate = "2024-01-01",
            status = "Released",
            tagline = "Test Tagline",
            title = "Test Movie",
            voteAverage = 7.8,
            voteCount = 1000
        )
        val result = CustomResult.Success(expectedDetails)

        coEvery {
            getMovieDetailsUseCase.execute(GetMovieDetailsUseCase.Params(movieId))
        } returns flowOf(result)

        // When
        viewModel.getMovieDetails(movieId)
        advanceUntilIdle() // Let coroutine finish

        // Then
        val state = viewModel.viewState.value
        assertEquals(expectedDetails, state.movieDetails)
    }

    @Test
    fun `getMovieCredits updates state on failure`() = testScope.runTest {
        // Given
        val movieId = 123
        val errorMessage = "Failed to load movie credits"

        coEvery {
            getMovieCreditsUseCase.execute(GetMovieCreditsUseCase.Params(movieId))
        } returns flowOf(CustomResult.Failure(errorMessage))

        // When
        viewModel.getMovieCredits(movieId)
        advanceUntilIdle() // Wait for coroutine to complete

        // Then
        val state = viewModel.viewState.value
        assertEquals(errorMessage, state.error)
        // Optionally also check that movieCredits is null or empty
        assert(state.movieAuthors == null || state.movieAuthors?.isEmpty() == true)
    }
}
