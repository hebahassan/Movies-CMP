package com.example.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature.home.domain.repo.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HomeRepository): ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        handleIntent(HomeIntent.LoadData)
    }

    fun handleIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.LoadData -> getTrendingMovies()

            is HomeIntent.MovieClicked -> {
                /*Todo: Navigation*/
            }
        }
    }

    private fun getTrendingMovies() {
        viewModelScope.launch {
            _state.update {
                it.copy(trendingMovies = HomeStateMachine.Loading)
            }
            try {
                val trendingMovies = repository.getTrendingMovies()
                _state.update {
                    it.copy(trendingMovies = HomeStateMachine.Success(data = trendingMovies))
                }
            } catch (_: Exception) {
                _state.update {
                    it.copy(trendingMovies = HomeStateMachine.Error(message = "No trending movies"))
                }
            }
        }
    }
}