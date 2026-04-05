package com.example.feature.details.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.core.ui.components.ErrorBanner
import com.example.core.ui.components.LoadingComponent
import com.example.core.ui.theme.Dimens
import com.example.feature.details.domain.model.MovieDetails
import com.example.feature.details.presentation.component.MovieHeader
import com.example.feature.details.presentation.component.RatingStars
import moviescmp.core.ui.generated.resources.Res
import moviescmp.core.ui.generated.resources.connection_error
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(
    state: MovieDetailsState,
    onIntent: (MovieDetailsIntent) -> Unit,
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { onIntent(MovieDetailsIntent.ShareMovie) },
                        enabled = state.movieDetails is DetailsStateMachine.Success
                    ) {
                        Icon(
                            Icons.Default.Share,
                            contentDescription = "Share"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
        ) {
            when (val details = state.movieDetails) {
                DetailsStateMachine.Loading -> LoadingComponent()

                is DetailsStateMachine.Success -> MovieDetailsContent(movie = details.data)

                DetailsStateMachine.Error -> ErrorBanner(
                    message = stringResource(Res.string.connection_error)
                )
            }
        }
    }
}

@Composable
fun MovieDetailsContent(movie: MovieDetails) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item(key = "header") {
            MovieHeader(movie)
        }

        item(key = "title") {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(
                    start = Dimens.paddingLarge,
                    end = 140.dp,
                    top = 80.dp
                )
            )
        }

        movie.tagline?.let {
            item(key = "tagline") {
                Text(
                    text = movie.tagline,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(
                        horizontal = Dimens.paddingLarge,
                        vertical = Dimens.paddingSmall
                    )
                )
            }
        }

        item(key = "chips") {
            FlowRow(
                modifier = Modifier.padding(
                    horizontal = Dimens.paddingLarge,
                    vertical = Dimens.paddingMedium
                ),
                horizontalArrangement = Arrangement.spacedBy(Dimens.paddingMedium),
                verticalArrangement = Arrangement.spacedBy(Dimens.paddingMedium)
            ) {
                movie.runtime?.let {
                    AssistChip(
                        onClick = {},
                        label = { Text("$it min") }
                    )
                }

                movie.releaseDate?.let {
                    AssistChip(
                        onClick = {},
                        label = { Text(it) }
                    )
                }

                movie.genres.forEach { genre ->
                    AssistChip(
                        onClick = {},
                        label = { Text(genre) }
                    )
                }
            }
        }

        item(key = "overview") {
            Text(
                text = "Overview",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(
                    horizontal = Dimens.paddingLarge,
                    vertical = Dimens.paddingMedium
                )
            )
            Text(
                text = movie.overview,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.padding(horizontal = Dimens.paddingLarge)
            )
        }

        item(key = "rating") {
            RatingStars(
                rating = movie.rating,
                modifier = Modifier.padding(Dimens.paddingLarge)
            )
        }
    }
}