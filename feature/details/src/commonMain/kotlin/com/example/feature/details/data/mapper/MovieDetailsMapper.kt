package com.example.feature.details.data.mapper

import com.example.core.network.ApiConstants
import com.example.feature.details.data.model.MovieDetailsDto
import com.example.feature.details.domain.model.MovieDetails
import kotlin.math.roundToInt

fun MovieDetailsDto.toDomain(): MovieDetails {
    return MovieDetails(
        id = id,
        title = title,
        overview = overview,
        tagline = tagline,
        posterUrl = posterPath?.let { "${ApiConstants.IMAGE_BASE_URL}$it" },
        backdropUrl = backdropPath?.let { "${ApiConstants.IMAGE_BASE_URL}$it" },
        rating = (voteAverage * 10.0).roundToInt() / 10.0,
        releaseDate = releaseDate,
        runtime = runtime,
        genres = genres.map { it.name }
    )
}