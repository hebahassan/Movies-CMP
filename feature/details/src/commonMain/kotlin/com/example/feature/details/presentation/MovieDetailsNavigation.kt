package com.example.feature.details.presentation

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MovieDetailsNavigationRoute() {
    val viewModel: MovieDetailsViewModel = koinViewModel()

    MovieDetailsScreen()
}

@Serializable
data class MovieDetailsRoute(val movieId: Int)