package com.example.moviescmp

import android.app.Application
import com.example.moviescmp.core.di.initKoin

class MoviesApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}