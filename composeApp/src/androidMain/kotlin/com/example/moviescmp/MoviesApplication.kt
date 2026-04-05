package com.example.moviescmp

import android.app.Application
import com.example.moviescmp.di.initKoin
import org.koin.android.ext.koin.androidContext

class MoviesApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MoviesApplication)
        }
    }
}