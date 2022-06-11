package com.kproject.programmingjokes.data.repository.database

import com.kproject.programmingjokes.data.database.FavoritesDAO
import com.kproject.programmingjokes.domain.mapper.fromDomain
import com.kproject.programmingjokes.domain.mapper.toDomain
import com.kproject.programmingjokes.domain.model.Joke
import com.kproject.programmingjokes.domain.repository.database.FavoriteJokesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteJokesRepositoryImpl(
    private val favoritesDAO: FavoritesDAO
) : FavoriteJokesRepository {

    override fun getAllFavorites(): Flow<List<Joke>> {
        return favoritesDAO.getAllFavorites().map { favoritesEntity ->
            favoritesEntity.map { favoriteJokeEntity ->
                favoriteJokeEntity.toDomain()
            }
        }
    }

    override suspend fun addFavorite(joke: Joke) {
        favoritesDAO.addFavorite(joke.fromDomain())
    }

    override suspend fun deleteFavorite(joke: Joke) {
        favoritesDAO.deleteFavorite(joke.fromDomain())
    }

    override suspend fun favoriteExists(joke: Joke): Boolean {
        return favoritesDAO.favoriteExists(joke.fromDomain().id) > 0
    }
}