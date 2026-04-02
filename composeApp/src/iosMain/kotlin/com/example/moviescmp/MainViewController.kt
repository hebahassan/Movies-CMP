package com.example.moviescmp

import androidx.compose.ui.window.ComposeUIViewController
import com.example.moviescmp.core.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) {
    App()
}