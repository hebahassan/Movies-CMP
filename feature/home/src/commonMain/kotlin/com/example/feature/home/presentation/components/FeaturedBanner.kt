package com.example.feature.home.presentation.components

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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.core.ui.components.RatingComponent
import com.example.core.ui.theme.Dimens
import com.example.feature.home.domain.model.Movie
import moviescmp.core.ui.generated.resources.Res

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