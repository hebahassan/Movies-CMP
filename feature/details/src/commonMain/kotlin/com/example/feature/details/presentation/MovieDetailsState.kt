package com.example.feature.details.presentation

import com.example.feature.details.domain.model.MovieDetails

sealed interface DetailsStateMachine {

    data object Loading: DetailsStateMachine

    data class Success(val data: MovieDetails): DetailsStateMachine

    data object Error: DetailsStateMachine
}

sealed interface DetailsEffect {
    data class ShareMovie(val movieId: Int, val movieTitle: String): DetailsEffect
}

data class MovieDetailsState(
    val movieDetails: DetailsStateMachine = DetailsStateMachine.Loading
)