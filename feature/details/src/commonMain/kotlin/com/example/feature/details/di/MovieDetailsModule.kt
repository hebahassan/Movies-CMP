package com.example.feature.details.di

import com.example.feature.details.data.remote.MovieDetailsApiService
import com.example.feature.details.data.repo.MovieDetailsRepositoryImpl
import com.example.feature.details.domain.repo.MovieDetailsRepository
import com.example.feature.details.presentation.MovieDetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val movieDetailsModule = module {
    single { MovieDetailsApiService(client = get()) }
    single<MovieDetailsRepository> { MovieDetailsRepositoryImpl(apiService = get()) }
    viewModel { MovieDetailsViewModel(savedStateHandle = get(), repository = get()) }
}