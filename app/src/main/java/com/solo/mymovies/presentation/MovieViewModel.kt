package com.solo.mymovies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.solo.mymovies.data.CustomResult
import com.solo.mymovies.domain.useCase.GetMovieCreditsUseCase
import com.solo.mymovies.domain.useCase.GetMovieDetailsUseCase
import com.solo.mymovies.domain.useCase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getMovieCreditsUseCase: GetMovieCreditsUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(MovieViewState())
    val viewState: StateFlow<MovieViewState> = _viewState

    // Scope the paging flow to the view model lifecycle
    val popularMovies = getPopularMoviesUseCase().cachedIn(viewModelScope)

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            getMovieDetailsUseCase.execute(GetMovieDetailsUseCase.Params(movieId = movieId))
                .collectLatest { result ->
                    when (result) {
                        is CustomResult.Success -> {
                            Timber.d("The movie details are: ${result.data}")
                            _viewState.update {
                                it.copy(
                                    movieDetails = result.data
                                )
                            }
                        }

                        is CustomResult.Failure -> {
                            Timber.d("Error getting movie details ...")
                        }
                    }
                }
        }
    }

    fun getMovieCredits(movieId: Int) {
        viewModelScope.launch {
            getMovieCreditsUseCase.execute(GetMovieCreditsUseCase.Params(movieId = movieId))
                .collectLatest { result ->
                    when (result) {
                        is CustomResult.Success -> {
                            Timber.d("The movie details are: ${result.data}")
                            _viewState.update {
                                it.copy(
                                    movieAuthors = result.data
                                )
                            }
                        }

                        is CustomResult.Failure -> {
                            Timber.d("Error getting movie credits ...")
                        }
                    }
                }
        }
    }
}