package com.example.core.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage
import com.example.core.ui.Dimens
import moviescmp.core.ui.generated.resources.Res

@Composable
fun RatingComponent(rating: Double) {
    Surface(
        shape = RoundedCornerShape(Dimens.paddingMedium),
        color = MaterialTheme.colorScheme.primary /*TODO: platform-specific*/
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = Dimens.paddingMedium, vertical = Dimens.paddingSmall),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = Res.getUri("drawable/ic_star.svg"),
                contentDescription = "Rating",
                modifier = Modifier.size(Dimens.iconSmall),
            )

            Spacer(modifier = Modifier.width(Dimens.paddingSmall))

            Text(
                text = "$rating / 10",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}