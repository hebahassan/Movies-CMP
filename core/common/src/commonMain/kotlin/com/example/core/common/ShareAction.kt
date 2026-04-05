package com.example.core.common

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class ShareService {
    fun shareMovie(movieId: Int, movieTitle: String)
}