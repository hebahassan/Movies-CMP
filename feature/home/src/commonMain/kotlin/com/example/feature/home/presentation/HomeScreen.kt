package com.example.feature.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import com.example.core.ui.Dimens
import com.example.core.ui.components.ErrorBanner
import com.example.core.ui.components.LoadingComponent
import com.example.core.ui.components.RatingComponent
import com.example.feature.home.domain.model.Movie
import kotlinx.coroutines.flow.SharedFlow
import moviescmp.core.ui.generated.resources.Res
import moviescmp.core.ui.generated.resources.coming_soon
import moviescmp.core.ui.generated.resources.no_trending_movies
import moviescmp.core.ui.generated.resources.no_upcoming_movies
import moviescmp.core.ui.generated.resources.trending_this_week
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeScreen(
    state: HomeState,
    effect: SharedFlow<HomeEffect>,
    onIntent: (HomeIntent) -> Unit
) {
    LaunchedEffect(Unit) {
        effect.collect { effect ->
            when (effect) {
                is HomeEffect.NavigateToMovieDetails -> { /*Todo: navigation callback*/ }
            }
        }
    }

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

@Composable
fun FeaturedBanner(
    movies: List<Movie>,
    onMovieClick: (Int) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { movies.size })

    Column(
        modifier = Modifier.padding(vertical = Dimens.paddingLarge)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.bannerHeight)
        ) { page ->
            BannerMovie(
                movie = movies[page],
                onMovieClick = { onMovieClick(movies[page].id) }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimens.paddingMedium),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(movies.size) { index ->
                val color = if (pagerState.currentPage == index)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)

                Box(
                    modifier = Modifier
                        .padding(horizontal = Dimens.indicatorSpacing)
                        .size(Dimens.indicatorSize)
                        .clip(RoundedCornerShape(50))
                        .then(Modifier.drawBehind { drawRect(color) })
                )
            }
        }
    }
}

@Composable
fun BannerMovie(
    movie: Movie,
    onMovieClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.paddingLarge)
            .clip(RoundedCornerShape(Dimens.cornerLarge))
            .clickable { onMovieClick() }
    ) {
        AsyncImage(
            model = movie.backdropUrl,
            placeholder = rememberAsyncImagePainter(
                Res.getUri("drawable/ic_placeholder.svg")
            ),
            error = rememberAsyncImagePainter(
                Res.getUri("drawable/ic_placeholder.svg")
            ),
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .align(Alignment.BottomCenter)
                .drawBehind {
                    drawRect(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f))
                        )
                    )
                }
        )

        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(Dimens.paddingLarge)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = movie.title,
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(1f)
            )

            RatingComponent(rating = movie.rating)
        }
    }
}

@Composable
fun UpcomingMovie(
    movie: Movie,
    onMovieClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onMovieClick() }
            .padding(horizontal = Dimens.paddingLarge, vertical = Dimens.paddingMedium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = movie.posterUrl,
            placeholder = rememberAsyncImagePainter(
                Res.getUri("drawable/ic_placeholder.svg")
            ),
            error = rememberAsyncImagePainter(
                Res.getUri("drawable/ic_placeholder.svg")
            ),
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(100.dp)
                .height(120.dp)
                .clip(RoundedCornerShape(Dimens.cornerSmall))
        )

        Column(
            modifier = Modifier
                .padding(start = Dimens.paddingMedium),
            verticalArrangement = Arrangement.spacedBy(Dimens.paddingLarge)
        ) {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                maxLines = 2
            )

            movie.releaseDate?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }
}