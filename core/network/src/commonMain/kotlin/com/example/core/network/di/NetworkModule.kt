package com.example.core.network.di

import com.example.core.common.DiConstants
import com.example.core.network.HttpClientFactory
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {
    single { HttpClientFactory.create(get(named(DiConstants.ACCESS_TOKEN))) }
}
