package com.example.feature.home.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeNavigationRoute(
    onDetailsNavigation: (movieId: Int) -> Unit
) {
    val viewModel: HomeViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is HomeEffect.NavigateToMovieDetails -> onDetailsNavigation(effect.movieId)
            }
        }
    }

    HomeScreen(
        state = state,
        onIntent = viewModel::handleIntent
    )
}

@Serializable
data object HomeRoute