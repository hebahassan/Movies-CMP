package com.example.feature.home.presentation

import androidx.compose.runtime.Immutable
import com.example.feature.home.domain.model.Movie

sealed interface HomeStateMachine<out T> {
    data object Loading: HomeStateMachine<Nothing>

    @Immutable
    data class Success<T>(val data: T): HomeStateMachine<T>

    data class Error(val message: String): HomeStateMachine<Nothing>
}

sealed interface HomeEffect {
    data class ShowErrorMessage(val message: String): HomeEffect

    data class NavigateToMovieDetails(val movieId: Int): HomeEffect
}

data class HomeState(
    val trendingMovies: HomeStateMachine<List<Movie>> = HomeStateMachine.Loading
)