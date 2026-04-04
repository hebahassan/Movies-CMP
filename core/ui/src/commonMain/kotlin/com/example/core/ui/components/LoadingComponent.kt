package com.example.core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.core.ui.theme.Dimens

@Composable
fun LoadingComponent() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.bannerHeight),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}