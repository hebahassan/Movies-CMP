package com.example.moviescmp.core.di

import org.koin.dsl.module

val appModule = module {
    includes(
        networkModule
    )
}