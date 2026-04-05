package com.example.feature.details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.feature.details.domain.repo.MovieDetailsRepository
import kotlinx.coroutines.flow.MutableStateFlow
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

    init {
        loadMovieDetails()
    }

    fun handleIntent(intent: MovieDetailsIntent) {
        when (intent) {
            MovieDetailsIntent.ShareMovie -> { /*todo*/ }
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
}