package com.example.core.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Primary,
    secondary = SecondaryDark,
    background = BackgroundDark,
    surface = SurfaceDark,
    errorContainer = ErrorContainerDark,
    onPrimary = OnPrimary,
    onSecondary = OnSecondary,
    onBackground = OnPrimary,
    onSurface = OnPrimary,
    onErrorContainer = OnErrorContainerDark,
)

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    secondary = SecondaryLight,
    background = BackgroundLight,
    surface = SurfaceLight,
    errorContainer = ErrorContainerLight,
    onPrimary = Color.Black,
    onSecondary = OnSecondary,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onErrorContainer = OnErrorContainerLight,
)

@Composable
fun MoviesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = MoviesTypography,
        content = content
    )
}