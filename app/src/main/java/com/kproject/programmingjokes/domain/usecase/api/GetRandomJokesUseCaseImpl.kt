package com.kproject.programmingjokes.domain.usecase.api

import com.kproject.programmingjokes.commom.DataState
import com.kproject.programmingjokes.domain.model.Joke
import com.kproject.programmingjokes.domain.repository.api.JokesRepository
import kotlinx.coroutines.flow.Flow

class GetRandomJokesUseCaseImpl(
    private val jokesRepository: JokesRepository
) : GetRandomJokesUseCase {

    override suspend fun invoke(): Flow<DataState<List<Joke>>> {
        return jokesRepository.getRandomJokes()
    }
}