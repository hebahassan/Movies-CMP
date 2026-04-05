package com.example.moviescmp.di

import com.example.core.network.di.networkModule
import com.example.moviescmp.utils.AppSecrets
import com.example.core.common.DiConstants
import com.example.core.common.platformModule
import com.example.feature.details.di.movieDetailsModule
import com.example.feature.home.di.homeModule
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)

        modules(
            module {
                single<String>(named(DiConstants.ACCESS_TOKEN)) {
                    AppSecrets.accessToken
                }
            },
            networkModule,
            homeModule,
            movieDetailsModule,
            platformModule
        )
    }
}