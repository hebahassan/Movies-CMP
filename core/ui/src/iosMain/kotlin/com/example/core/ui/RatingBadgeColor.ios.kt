package com.example.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.get
import platform.CoreGraphics.CGColorGetComponents
import platform.UIKit.UIColor
import platform.UIKit.systemBlueColor

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun ratingBadgeColor(): Color {
    val components = CGColorGetComponents(UIColor.systemBlueColor.CGColor)
    return if (components != null) {
        Color(
            red = components[0].toFloat(),
            green = components[1].toFloat(),
            blue = components[2].toFloat(),
            alpha = components[3].toFloat()
        )
    } else {
        Color.Blue
    }
}