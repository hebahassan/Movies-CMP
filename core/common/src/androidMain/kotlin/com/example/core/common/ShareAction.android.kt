package com.example.core.common

import android.content.Context
import android.content.Intent

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class ShareService(private val context: Context) {

    actual fun shareMovie(movieId: Int, movieTitle: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "Check out $movieTitle")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(
            Intent.createChooser(intent, "Share Movie")
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }
}