package com.example.feature.home.di

import com.example.feature.home.data.remote.HomeApiService
import com.example.feature.home.data.repo.HomeRepositoryImpl
import com.example.feature.home.domain.repo.HomeRepository
import com.example.feature.home.domain.usecase.GetFilteredTopRatedMoviesUseCase
import com.example.feature.home.presentation.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    single { HomeApiService(client = get()) }
    single<HomeRepository> { HomeRepositoryImpl(apiService = get()) }
    single { GetFilteredTopRatedMoviesUseCase() }
    viewModel { HomeViewModel(repository = get(), filteredTopRatedMoviesUseCase = get()) }
}