package com.example.feature.home.domain.repo

import com.example.feature.home.domain.model.Genre
import com.example.feature.home.domain.model.Movie

interface HomeRepository {

    suspend fun getTrendingMovies(): List<Movie>

    suspend fun getUpcomingMovies(): List<Movie>

    suspend fun getTopRatedMovies(): List<Movie>

    suspend fun getGenres(): List<Genre>
}