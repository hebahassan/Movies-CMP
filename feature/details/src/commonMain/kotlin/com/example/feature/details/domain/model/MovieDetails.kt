package com.example.feature.details.domain.model

data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String,
    val tagline: String?,
    val posterUrl: String?,
    val backdropUrl: String?,
    val rating: Double,
    val releaseDate: String?,
    val runtime: Int?,
    val genres: List<String>
)