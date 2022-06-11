package com.kproject.programmingjokes.domain.usecase.database.add

import com.kproject.programmingjokes.data.database.FavoritesDAO
import com.kproject.programmingjokes.domain.mapper.fromDomain
import com.kproject.programmingjokes.domain.model.Joke
import com.kproject.programmingjokes.domain.repository.database.FavoriteJokesRepository

class AddFavoriteUseCaseImpl(
    private val favoriteJokesRepository: FavoriteJokesRepository
) : AddFavoriteUseCase {

    override suspend fun invoke(joke: Joke) {
        favoriteJokesRepository.addFavorite(joke)
    }
}