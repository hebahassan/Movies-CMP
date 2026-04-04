package com.example.feature.home.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import com.example.core.ui.theme.Dimens
import com.example.feature.home.domain.model.Movie
import moviescmp.core.ui.generated.resources.Res

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