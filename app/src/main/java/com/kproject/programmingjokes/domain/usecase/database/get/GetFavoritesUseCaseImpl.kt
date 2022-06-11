package com.kproject.programmingjokes.domain.usecase.database.get

import com.kproject.programmingjokes.domain.model.Joke
import com.kproject.programmingjokes.domain.repository.database.FavoriteJokesRepository
import kotlinx.coroutines.flow.Flow

class GetFavoritesUseCaseImpl(
    private val favoriteJokesRepository: FavoriteJokesRepository
) : GetFavoritesUseCase {

    override suspend fun invoke(): Flow<List<Joke>> {
        return favoriteJokesRepository.getAllFavorites()
    }
}