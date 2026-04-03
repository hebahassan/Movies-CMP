package com.example.moviescmp

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.ui.MoviesTheme
import com.example.moviescmp.navigation.AppNavHost

@Composable
@Preview
fun App() {
    MoviesTheme {
        AppNavHost()
    }
}