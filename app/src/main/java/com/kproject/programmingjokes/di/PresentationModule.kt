package com.kproject.programmingjokes.di

import com.kproject.programmingjokes.presentation.favorites.FavoritesViewModel
import com.kproject.programmingjokes.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        HomeViewModel(
            getRandomJokesUseCase = get(),
            addFavoriteUseCase = get(),
            deleteFavoriteUseCase = get(),
            favoriteExistsUseCase = get()
        )
    }

    viewModel {
        FavoritesViewModel(
            getFavoritesUseCase = get(),
            deleteFavoriteUseCase = get()
        )
    }
}