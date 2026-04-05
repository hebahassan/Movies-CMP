package com.example.feature.details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.feature.details.domain.repo.MovieDetailsRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: MovieDetailsRepository
): ViewModel() {

    private val movieId = savedStateHandle.toRoute<MovieDetailsRoute>().movieId

    private val _state = MutableStateFlow(MovieDetailsState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<DetailsEffect>()
    val effect = _effect.asSharedFlow()

    init {
        loadMovieDetails()
    }

    fun handleIntent(intent: MovieDetailsIntent) {
        when (intent) {
            MovieDetailsIntent.ShareMovie -> onShareClicked()
        }
    }

    private fun loadMovieDetails() {
        viewModelScope.launch {
            _state.update {
                it.copy(movieDetails = DetailsStateMachine.Loading)
            }
            runCatching {
                repository.getMovieDetails(movieId)
            }.onSuccess { details ->
                _state.update {
                    it.copy(movieDetails = DetailsStateMachine.Success(details))
                }
            }.onFailure {
                _state.update {
                    it.copy(movieDetails = DetailsStateMachine.Error)
                }
            }
        }
    }

    private fun onShareClicked() {
        val details = (_state.value.movieDetails as? DetailsStateMachine.Success)?.data ?: return
        viewModelScope.launch {
            _effect.emit(
                DetailsEffect.ShareMovie(details.id, details.title)
            )
        }
    }
}