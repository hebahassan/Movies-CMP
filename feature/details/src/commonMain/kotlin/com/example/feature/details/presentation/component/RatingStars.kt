package com.example.feature.details.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.core.ui.theme.Dimens
import com.example.core.ui.theme.RatingStarsColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment

@Composable
fun RatingStars(
    modifier: Modifier = Modifier,
    rating: Double
) {
    val filledStars = (rating / 2).toInt()
    val hasHalf = (rating / 2) - filledStars >= 0.5

    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$rating/10",
            style = MaterialTheme.typography.bodyMedium,
            color = RatingStarsColor,
            modifier = Modifier.padding(end = Dimens.paddingMedium)
        )

        repeat(filledStars) {
            Icon(
                Icons.Default.Star,
                contentDescription = null,
                tint = RatingStarsColor,
                modifier = Modifier.size(Dimens.iconMedium)
            )
        }

        if (hasHalf) {
            Icon(
                Icons.AutoMirrored.Filled.StarHalf,
                contentDescription = null,
                tint = RatingStarsColor,
                modifier = Modifier.size(Dimens.iconMedium)
            )
        }

        repeat(5 - filledStars - if (hasHalf) 1 else 0) {
            Icon(
                Icons.Default.StarBorder,
                contentDescription = null,
                tint = RatingStarsColor,
                modifier = Modifier.size(Dimens.iconMedium)
            )
        }
    }
}