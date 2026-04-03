package com.example.feature.home.data.remote

import com.example.core.network.ApiConstants
import com.example.feature.home.data.model.MoviesResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class HomeApiService(private val client: HttpClient) {

    suspend fun getTrendingMovies(): MoviesResponseDto {
        return client.get(ApiConstants.Endpoints.TRENDING_MOVIES_URL).body()
    }
}