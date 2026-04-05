package com.example.feature.details.domain.repo

import com.example.feature.details.domain.model.MovieDetails

interface MovieDetailsRepository {

    suspend fun getMovieDetails(movieId: Int): MovieDetails
}