package com.example.core.network

object ApiConstants {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

    object Endpoints {
        const val TRENDING_MOVIES_URL = "trending/movie/week"
        const val TOP_RATED_MOVIES_URL = "movie/top_rated"
        const val GENRE_LIST_URL = "genre/movie/list"
        const val UPCOMING_MOVIES_URL = "movie/upcoming"
    }
}