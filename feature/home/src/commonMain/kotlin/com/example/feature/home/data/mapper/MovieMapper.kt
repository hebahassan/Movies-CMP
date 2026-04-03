package com.example.feature.home.data.mapper

import com.example.core.network.ApiConstants
import com.example.feature.home.data.model.MovieDto
import com.example.feature.home.domain.model.Movie
import kotlin.math.roundToInt

fun MovieDto.toDomain(): Movie = Movie(
    id = id,
    title = title,
    overview = overview,
    posterUrl = posterPath?.let { "${ApiConstants.IMAGE_BASE_URL}$it" },
    backdropUrl = backdropPath?.let { "${ApiConstants.IMAGE_BASE_URL}$it" },
    rating = (voteAverage * 10.0).roundToInt() / 10.0,
    releaseDate = releaseDate,
    genreIds = genreIds
)