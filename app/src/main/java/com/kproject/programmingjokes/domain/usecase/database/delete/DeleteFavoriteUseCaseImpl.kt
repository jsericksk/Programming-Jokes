package com.kproject.programmingjokes.domain.usecase.database.delete

import com.kproject.programmingjokes.domain.model.Joke
import com.kproject.programmingjokes.domain.repository.database.FavoriteJokesRepository

class DeleteFavoriteUseCaseImpl(
    private val favoriteJokesRepository: FavoriteJokesRepository
) : DeleteFavoriteUseCase {

    override suspend fun invoke(joke: Joke) {
        favoriteJokesRepository.deleteFavorite(joke)
    }
}