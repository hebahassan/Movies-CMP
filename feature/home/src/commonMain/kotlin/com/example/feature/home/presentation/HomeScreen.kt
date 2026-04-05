package com.example.feature.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.core.ui.theme.Dimens
import com.example.core.ui.components.ErrorBanner
import com.example.core.ui.components.LoadingComponent
import com.example.feature.home.presentation.components.FeaturedBanner
import com.example.feature.home.presentation.components.TopRatedMovie
import com.example.feature.home.presentation.components.UpcomingMovie
import moviescmp.core.ui.generated.resources.Res
import moviescmp.core.ui.generated.resources.all_movies
import moviescmp.core.ui.generated.resources.coming_soon
import moviescmp.core.ui.generated.resources.no_filtered_movies
import moviescmp.core.ui.generated.resources.no_trending_movies
import moviescmp.core.ui.generated.resources.no_upcoming_movies
import moviescmp.core.ui.generated.resources.top_rated
import moviescmp.core.ui.generated.resources.trending_this_week
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeScreen(
    state: HomeState,
    onIntent: (HomeIntent) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        item(key = "Trending Header") {
            Text(
                text = stringResource(Res.string.trending_this_week),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(horizontal = Dimens.paddingLarge, vertical = Dimens.paddingXLarge)
            )
        }

        item(key = "Trending Banner") {
            when (val trending = state.trendingMovies) {
                HomeStateMachine.Loading -> LoadingComponent()

                is HomeStateMachine.Success -> FeaturedBanner(
                    movies = trending.data,
                    onMovieClick = { movieId ->
                        onIntent(HomeIntent.MovieClicked(movieId))
                    }
                )

                is HomeStateMachine.Error -> ErrorBanner(
                    modifier = Modifier.padding(all = Dimens.paddingLarge),
                    message = stringResource(Res.string.no_trending_movies)
                )
            }
        }

        item(key = "Top Rated Header") {
            Text(
                text = stringResource(Res.string.top_rated),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .padding(horizontal = Dimens.paddingLarge, vertical = Dimens.paddingXLarge)
            )
        }

        item(key = "Genres") {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = Dimens.paddingLarge),
                horizontalArrangement = Arrangement.spacedBy(Dimens.paddingMedium)
            ) {
                item {
                    FilterChip(
                        selected = state.selectedGenreId == null,
                        onClick = { onIntent(HomeIntent.GenreSelected(null)) },
                        label = { Text(stringResource(Res.string.all_movies)) }
                    )
                }

                items(
                    items = state.genres,
                    key = { genre -> genre.id }
                ) { genre ->
                    FilterChip(
                        selected = state.selectedGenreId == genre.id,
                        onClick = { onIntent(HomeIntent.GenreSelected(genre.id)) },
                        label = { Text(genre.name) }
                    )
                }
            }
        }

        item(key = "Top Rated List") {
            when (state.topRatedMovies) {
                HomeStateMachine.Loading -> LoadingComponent()

                is HomeStateMachine.Success -> {
                    if (state.filteredTopRatedMovies.isNotEmpty()) {
                        val listState = rememberLazyListState()

                        LaunchedEffect(state.selectedGenreId) {
                            listState.animateScrollToItem(0)
                        }

                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            state = listState,
                            contentPadding = PaddingValues(horizontal = Dimens.paddingLarge),
                            horizontalArrangement = Arrangement.spacedBy(Dimens.paddingMedium)
                        ) {
                            items(
                                items = state.filteredTopRatedMovies,
                                key = { movie -> movie.id }
                            ) { movie ->
                                TopRatedMovie(
                                    movie = movie,
                                    onClick = {
                                        onIntent(HomeIntent.MovieClicked(movie.id))
                                    }
                                )
                            }
                        }
                    } else {
                        ErrorBanner(
                            modifier = Modifier.padding(vertical = Dimens.paddingLarge),
                            message = stringResource(Res.string.no_filtered_movies)
                        )
                    }
                }

                HomeStateMachine.Error -> ErrorBanner(
                    modifier = Modifier.padding(all = Dimens.paddingLarge),
                    message = stringResource(Res.string.no_filtered_movies)
                )
            }
        }

        item(key = "Upcoming Header") {
            Text(
                text = stringResource(Res.string.coming_soon),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .padding(horizontal = Dimens.paddingLarge, vertical = Dimens.paddingXLarge)
            )
        }

        when (val upcoming = state.upcomingMovies) {
            HomeStateMachine.Loading -> item(key = "Upcoming Loading") {
                LoadingComponent()
            }

            is HomeStateMachine.Success -> items(
                items = upcoming.data,
                key = { movie -> movie.id }
            ) { movie ->
                UpcomingMovie(
                    movie = movie,
                    onMovieClick = {
                        onIntent(HomeIntent.MovieClicked(movie.id))
                    }
                )
            }

            is HomeStateMachine.Error -> item(key = "Upcoming Error") {
                ErrorBanner(
                    modifier = Modifier.padding(all = Dimens.paddingLarge),
                    message = stringResource(Res.string.no_upcoming_movies)
                )
            }
        }
    }
}