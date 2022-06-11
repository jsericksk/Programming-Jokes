package com.kproject.programmingjokes.domain.usecase.database

import com.kproject.programmingjokes.domain.model.Joke
import com.kproject.programmingjokes.domain.repository.database.FavoriteJokesRepository

class FavoriteExistsUseCaseImpl(
    private val favoriteJokesRepository: FavoriteJokesRepository
) : FavoriteExistsUseCase {

    override suspend fun invoke(joke: Joke): Boolean {
        return favoriteJokesRepository.favoriteExists(joke)
    }
}