package com.example.feature.home.presentation

import androidx.compose.runtime.Immutable
import com.example.feature.home.domain.model.Genre
import com.example.feature.home.domain.model.Movie

sealed interface HomeStateMachine<out T> {
    data object Loading: HomeStateMachine<Nothing>

    @Immutable
    data class Success<T>(val data: T): HomeStateMachine<T>

    data object Error: HomeStateMachine<Nothing>
}

sealed interface HomeEffect {
    data class NavigateToMovieDetails(val movieId: Int): HomeEffect
}

data class HomeState(
    val trendingMovies: HomeStateMachine<List<Movie>> = HomeStateMachine.Loading,
    val upcomingMovies: HomeStateMachine<List<Movie>> = HomeStateMachine.Loading,
    val topRatedMovies: HomeStateMachine<List<Movie>> = HomeStateMachine.Loading,
    val filteredTopRatedMovies: List<Movie> = emptyList(),
    val genres: List<Genre> = emptyList(),
    val selectedGenreId: Int? = null
)