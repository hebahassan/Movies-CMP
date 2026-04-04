package com.example.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature.home.domain.model.Movie
import com.example.feature.home.domain.repo.HomeRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class HomeViewModel(private val repository: HomeRepository): ViewModel() {

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

            is HomeIntent.MovieClicked -> {
                /*Todo: Navigation*/
            }
        }
    }

    private fun fetchApisData() {
        viewModelScope.launch {
            supervisorScope {
                launch(
                    CoroutineExceptionHandler { _, _ ->
                        onTrendingMoviesState(HomeStateMachine.Error)
                    }
                ) { getTrendingMovies() }

                launch (
                    CoroutineExceptionHandler { _, _ ->
                        onUpcomingMoviesState(HomeStateMachine.Error)
                    }
                ){ getUpcomingMovies() }
            }
        }
    }

    private suspend fun getTrendingMovies() {
        onTrendingMoviesState(HomeStateMachine.Loading)
        try {
            val trendingMovies = repository.getTrendingMovies()
            onTrendingMoviesState(HomeStateMachine.Success(trendingMovies))
        } catch (_: Exception) {
            onTrendingMoviesState(HomeStateMachine.Error)
        }
    }

    private suspend fun getUpcomingMovies() {
        onUpcomingMoviesState(HomeStateMachine.Loading)
        try {
            val upcomingMovies = repository.getUpcomingMovies()
            onUpcomingMoviesState(HomeStateMachine.Success(upcomingMovies))
        } catch (_: Exception) {
            onUpcomingMoviesState(HomeStateMachine.Error)
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
}