package com.example.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature.home.domain.model.Movie
import com.example.feature.home.domain.repo.HomeRepository
import com.example.feature.home.domain.usecase.GetFilteredTopRatedMoviesUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class HomeViewModel(
    private val repository: HomeRepository,
    private val filteredTopRatedMoviesUseCase: GetFilteredTopRatedMoviesUseCase
): ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<HomeEffect>()
    val effect = _effect.asSharedFlow()

    init {
        handleIntent(HomeIntent.LoadData)
    }

    fun handleIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.LoadData -> fetchApisData()

            is HomeIntent.MovieClicked -> viewModelScope.launch {
                _effect.emit(HomeEffect.NavigateToMovieDetails(intent.movieId))
            }

            is HomeIntent.GenreSelected -> {
                _state.update { it.copy(selectedGenreId = intent.genreId) }
                filterTopRatedMovies()
            }
        }
    }

    private fun fetchApisData() {
        viewModelScope.launch {
            supervisorScope {
                launch { getTrendingMovies() }

                launch { getUpcomingMovies() }

                launch { getGenres() }

                launch { getTopRatedMovies() }
            }
        }
    }

    private suspend fun getTrendingMovies() {
        onTrendingMoviesState(HomeStateMachine.Loading)
        runCatching {
            repository.getTrendingMovies()
        }.onSuccess { movies ->
            onTrendingMoviesState(HomeStateMachine.Success(movies))
        }.onFailure {
            onTrendingMoviesState(HomeStateMachine.Error)
        }
    }

    private suspend fun getUpcomingMovies() {
        onUpcomingMoviesState(HomeStateMachine.Loading)
        runCatching {
            repository.getUpcomingMovies()
        }.onSuccess { movies ->
            onUpcomingMoviesState(HomeStateMachine.Success(movies))
        }.onFailure {
            onUpcomingMoviesState(HomeStateMachine.Error)
        }
    }

    private suspend fun getGenres() {
        runCatching {
            repository.getGenres()
        }.onSuccess { genres ->
            _state.update { it.copy(genres = genres) }
        }
    }

    private suspend fun getTopRatedMovies() {
        _state.update {
            it.copy(topRatedMovies = HomeStateMachine.Loading)
        }
        runCatching {
            repository.getTopRatedMovies()
        }.onSuccess { movies ->
            _state.update {
                it.copy(
                    topRatedMovies = HomeStateMachine.Success(movies),
                    filteredTopRatedMovies = movies
                )
            }
        }.onFailure {
            _state.update {
                it.copy(topRatedMovies = HomeStateMachine.Error)
            }
        }
    }

    private fun onTrendingMoviesState(state: HomeStateMachine<List<Movie>>) {
        _state.update {
            it.copy(trendingMovies = state)
        }
    }

    private fun onUpcomingMoviesState(state: HomeStateMachine<List<Movie>>) {
        _state.update {
            it.copy(upcomingMovies = state)
        }
    }

    private fun filterTopRatedMovies() {
        val movies = (state.value.topRatedMovies as? HomeStateMachine.Success)?.data ?: return
        val filteredList = filteredTopRatedMoviesUseCase(movies, state.value.selectedGenreId)
        _state.update {
            it.copy(filteredTopRatedMovies = filteredList)
        }
    }
}