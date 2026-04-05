package com.example.feature.home.presentation

sealed interface HomeIntent {
    data object LoadData: HomeIntent

    data class MovieClicked(val movieId: Int): HomeIntent

    data class GenreSelected(val genreId: Int?) : HomeIntent
}