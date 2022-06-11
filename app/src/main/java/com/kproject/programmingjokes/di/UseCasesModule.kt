package com.kproject.programmingjokes.di

import com.kproject.programmingjokes.data.repository.api.JokesRepositoryImpl
import com.kproject.programmingjokes.data.repository.database.FavoriteJokesRepositoryImpl
import com.kproject.programmingjokes.domain.repository.api.JokesRepository
import com.kproject.programmingjokes.domain.repository.database.FavoriteJokesRepository
import com.kproject.programmingjokes.domain.usecase.api.GetRandomJokesUseCase
import com.kproject.programmingjokes.domain.usecase.api.GetRandomJokesUseCaseImpl
import com.kproject.programmingjokes.domain.usecase.database.FavoriteExistsUseCase
import com.kproject.programmingjokes.domain.usecase.database.FavoriteExistsUseCaseImpl
import com.kproject.programmingjokes.domain.usecase.database.add.AddFavoriteUseCase
import com.kproject.programmingjokes.domain.usecase.database.add.AddFavoriteUseCaseImpl
import com.kproject.programmingjokes.domain.usecase.database.delete.DeleteFavoriteUseCase
import com.kproject.programmingjokes.domain.usecase.database.delete.DeleteFavoriteUseCaseImpl
import com.kproject.programmingjokes.domain.usecase.database.get.GetFavoritesUseCase
import com.kproject.programmingjokes.domain.usecase.database.get.GetFavoritesUseCaseImpl
import org.koin.dsl.module

val useCasesModule = module {
    single<JokesRepository> { JokesRepositoryImpl(get(), get()) }

    single<FavoriteJokesRepository> { FavoriteJokesRepositoryImpl(get()) }

    single<GetRandomJokesUseCase> { GetRandomJokesUseCaseImpl(get()) }

    single<GetFavoritesUseCase> { GetFavoritesUseCaseImpl(get()) }

    single<AddFavoriteUseCase> { AddFavoriteUseCaseImpl(get()) }

    single<DeleteFavoriteUseCase> { DeleteFavoriteUseCaseImpl(get()) }

    single<FavoriteExistsUseCase> { FavoriteExistsUseCaseImpl(get()) }
}