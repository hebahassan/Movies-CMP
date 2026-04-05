package com.example.core.common

import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single { ShareService(get()) }
}