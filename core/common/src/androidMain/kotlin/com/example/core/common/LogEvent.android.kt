package com.example.core.common

import android.util.Log

actual fun logEvent(
    eventName: String,
    params: Map<String, Any>
) {
    Log.d(
        "Analytics",
        "event_name = $eventName, parameters = ${params.entries.joinToString { "${it.key}: ${it.value}" }}"
    )
}