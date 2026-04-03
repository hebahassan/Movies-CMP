package com.example.moviescmp.utils

import com.example.moviescmp.BuildConfig

actual object AppSecrets {
    actual val accessToken: String
        get() = BuildConfig.TMDB_ACCESS_TOKEN
}