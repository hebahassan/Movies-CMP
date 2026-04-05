package com.example.feature.details.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.common.ShareService
import com.example.core.common.logEvent
import kotlinx.serialization.Serializable
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MovieDetailsNavigationRoute(
    onNavigateBack: () -> Unit
) {
    val viewModel: MovieDetailsViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val shareService: ShareService = koinInject()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is DetailsEffect.ShareMovie -> {
                    logEvent(
                        eventName = "movie_shared",
                        params = mapOf(
                            "movie_id" to effect.movieId,
                            "movie_title" to effect.movieTitle
                        )
                    )
                    shareService.shareMovie(effect.movieId, effect.movieTitle)
                }
            }
        }
    }

    MovieDetailsScreen(
        state = state,
        onIntent = viewModel::handleIntent,
        onNavigateBack = onNavigateBack
    )
}

@Serializable
data class MovieDetailsRoute(val movieId: Int)