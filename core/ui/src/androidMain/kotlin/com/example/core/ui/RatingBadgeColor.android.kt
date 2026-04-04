package com.example.core.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
actual fun ratingBadgeColor(): Color {
    return MaterialTheme.colorScheme.tertiary
}