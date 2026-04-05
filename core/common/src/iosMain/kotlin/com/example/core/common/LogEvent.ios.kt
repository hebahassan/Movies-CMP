package com.example.core.common

import platform.Foundation.NSLog

actual fun logEvent(
    eventName: String,
    params: Map<String, Any>
) {
    NSLog("event_name: $eventName, parameters = ${params.entries.joinToString { "${it.key}: ${it.value}" }}")
}