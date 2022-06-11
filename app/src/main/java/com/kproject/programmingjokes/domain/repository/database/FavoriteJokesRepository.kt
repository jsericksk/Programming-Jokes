package com.kproject.programmingjokes.domain.repository.database

import com.kproject.programmingjokes.domain.model.Joke
import kotlinx.coroutines.flow.Flow

interface FavoriteJokesRepository {

    fun getAllFavorites(): Flow<List<Joke>>

    suspend fun addFavorite(joke: Joke)

    suspend fun deleteFavorite(joke: Joke)

    suspend fun favoriteExists(joke: Joke): Boolean
}