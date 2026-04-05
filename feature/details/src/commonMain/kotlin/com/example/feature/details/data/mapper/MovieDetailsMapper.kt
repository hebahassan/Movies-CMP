package com.example.feature.details.data.mapper

import com.example.core.network.ApiConstants
import com.example.feature.details.data.model.MovieDetailsDto
import com.example.feature.details.domain.model.MovieDetails

fun MovieDetailsDto.toDomain(): MovieDetails {
    return MovieDetails(
        id = id,
        title = title,
        overview = overview,
        tagline = tagline,
        posterUrl = posterPath?.let { "${ApiConstants.IMAGE_BASE_URL}$it" },
        backdropUrl = backdropPath?.let { "${ApiConstants.IMAGE_BASE_URL}$it" },
        rating = voteAverage,
        releaseDate = releaseDate,
        runtime = runtime,
        genres = genres.map { it.name }
    )
}