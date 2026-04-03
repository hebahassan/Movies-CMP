package com.example.moviescmp

import android.app.Application
import com.example.moviescmp.di.initKoin

class MoviesApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}