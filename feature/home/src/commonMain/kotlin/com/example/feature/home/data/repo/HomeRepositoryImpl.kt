package com.example.feature.home.data.repo

import com.example.feature.home.data.mapper.toDomain
import com.example.feature.home.data.remote.HomeApiService
import com.example.feature.home.domain.model.Movie
import com.example.feature.home.domain.repo.HomeRepository

class HomeRepositoryImpl(private val apiService: HomeApiService): HomeRepository {

    override suspend fun getTrendingMovies(): List<Movie> {
        return apiService.getTrendingMovies().results.map {
            it.toDomain()
        }
    }
}