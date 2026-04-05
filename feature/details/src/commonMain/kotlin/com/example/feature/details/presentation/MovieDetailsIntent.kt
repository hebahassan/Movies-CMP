package com.example.feature.details.presentation

sealed interface MovieDetailsIntent {

    data object ShareMovie: MovieDetailsIntent
}