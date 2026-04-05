package com.example.moviescmp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feature.details.presentation.MovieDetailsNavigationRoute
import com.example.feature.details.presentation.MovieDetailsRoute
import com.example.feature.home.presentation.HomeNavigationRoute
import com.example.feature.home.presentation.HomeRoute

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {
        composable<HomeRoute> {
            HomeNavigationRoute (
                onDetailsNavigation = { movieId ->
                    navController.navigate(MovieDetailsRoute(movieId = movieId))
                }
            )
        }

        composable<MovieDetailsRoute> {
            MovieDetailsNavigationRoute(
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}