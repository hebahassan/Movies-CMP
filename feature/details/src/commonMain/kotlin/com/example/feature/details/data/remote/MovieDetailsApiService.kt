package com.example.feature.details.data.remote

import com.example.feature.details.data.model.MovieDetailsDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MovieDetailsApiService(private val client: HttpClient) {

    suspend fun getMovieDetails(movieId: Int): MovieDetailsDto {
        return client.get("movie/$movieId").body()
    }
}