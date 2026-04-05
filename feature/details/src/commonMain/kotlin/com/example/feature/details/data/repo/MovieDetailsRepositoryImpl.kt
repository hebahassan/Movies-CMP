package com.example.feature.details.data.repo

import com.example.feature.details.data.mapper.toDomain
import com.example.feature.details.data.remote.MovieDetailsApiService
import com.example.feature.details.domain.model.MovieDetails
import com.example.feature.details.domain.repo.MovieDetailsRepository

class MovieDetailsRepositoryImpl(
    private val apiService: MovieDetailsApiService
): MovieDetailsRepository {

    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return apiService.getMovieDetails(movieId).toDomain()
    }
}