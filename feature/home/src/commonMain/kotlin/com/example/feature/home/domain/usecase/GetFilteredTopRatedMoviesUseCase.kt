package com.example.feature.home.domain.usecase

import com.example.feature.home.domain.model.Movie

class GetFilteredTopRatedMoviesUseCase {

    operator fun invoke(
        movies: List<Movie>,
        genreId: Int?
    ): List<Movie> {
        return if (genreId == null) movies
        else movies.filter { movie -> movie.genreIds.contains(genreId) }
    }
}