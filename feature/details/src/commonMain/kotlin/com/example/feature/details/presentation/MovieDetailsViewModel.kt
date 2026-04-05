package com.example.feature.details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.example.core.common.logEvent
import com.example.feature.details.domain.repo.MovieDetailsRepository

class MovieDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: MovieDetailsRepository
): ViewModel() {

    private val movieId = savedStateHandle.toRoute<MovieDetailsRoute>().movieId

    init {
        val params = HashMap<String, Int>()
        params["movieId"] = movieId
        logEvent(
            eventName = "movieIdListener",
            params = params
        )
    }
}