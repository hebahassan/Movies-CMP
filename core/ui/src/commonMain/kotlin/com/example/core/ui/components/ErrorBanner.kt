package com.example.core.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ErrorBanner(
    modifier: Modifier = Modifier,
    message: String
) {
    Text(
        modifier = modifier,
        text = message,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onErrorContainer,
    )
}