package com.example.feature.home.domain.repo

import com.example.feature.home.domain.model.Movie

interface HomeRepository {

    suspend fun getTrendingMovies(): List<Movie>
}