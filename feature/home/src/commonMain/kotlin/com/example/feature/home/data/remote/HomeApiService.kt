package com.example.feature.home.data.remote

import com.example.core.network.ApiConstants
import com.example.feature.home.data.model.GenreResponseDto
import com.example.feature.home.data.model.MoviesResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class HomeApiService(private val client: HttpClient) {

    suspend fun getTrendingMovies(): MoviesResponseDto {
        return client.get(ApiConstants.Endpoints.TRENDING_MOVIES_URL).body()
    }

    suspend fun getUpcomingMovies(): MoviesResponseDto {
        return client.get(ApiConstants.Endpoints.UPCOMING_MOVIES_URL) {
            parameter("page", 1)
        }.body()
    }

    suspend fun getTopRatedMovies(): MoviesResponseDto {
        return client.get(ApiConstants.Endpoints.TOP_RATED_MOVIES_URL).body()
    }

    suspend fun getGenres(): GenreResponseDto {
        return client.get(ApiConstants.Endpoints.GENRE_LIST_URL).body()
    }
}