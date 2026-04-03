package com.example.moviescmp.utils

import platform.Foundation.NSBundle
import platform.Foundation.NSDictionary
import platform.Foundation.dictionaryWithContentsOfFile

actual object AppSecrets {
    actual val accessToken: String
        get() = getStringResource(
            fileName = "Secrets",
            fileType = "plist",
            valueKey = "accessToken"
        ) ?: ""
}

internal fun getStringResource(
    fileName: String,
    fileType: String,
    valueKey: String,
): String? {
    val result = NSBundle.mainBundle.pathForResource(fileName, fileType)?.let {
        val map = NSDictionary.dictionaryWithContentsOfFile(it)
        map?.get(valueKey) as? String
    }
    return result
}