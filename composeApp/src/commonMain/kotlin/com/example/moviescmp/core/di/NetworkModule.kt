package com.example.moviescmp.core.di

import com.example.moviescmp.core.network.HttpClientFactory
import com.example.moviescmp.core.util.AppSecrets
import org.koin.dsl.module

val networkModule = module {
    single { HttpClientFactory.create(AppSecrets.accessToken) }
}