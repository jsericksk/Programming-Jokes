package com.kproject.programmingjokes.domain.repository.api

import com.kproject.programmingjokes.commom.DataState
import com.kproject.programmingjokes.domain.model.Joke
import kotlinx.coroutines.flow.Flow

interface JokesRepository {
    suspend fun getRandomJokes(): Flow<DataState<List<Joke>>>
}