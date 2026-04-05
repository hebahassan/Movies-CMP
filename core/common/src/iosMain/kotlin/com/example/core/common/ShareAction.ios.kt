package com.example.core.common

import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class ShareService {
    actual fun shareMovie(movieId: Int, movieTitle: String) {
        val text = "Check out $movieTitle"
        val activityVC = UIActivityViewController(
            activityItems = listOf(text),
            applicationActivities = null
        )
        UIApplication.sharedApplication.keyWindow?.rootViewController?.presentViewController(
            activityVC, animated = true, completion = null
        )
    }
}