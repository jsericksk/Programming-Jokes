package com.kproject.programmingjokes.domain.usecase.api

import com.kproject.programmingjokes.commom.DataState
import com.kproject.programmingjokes.domain.model.Joke
import kotlinx.coroutines.flow.Flow

interface GetRandomJokesUseCase {
    suspend operator fun invoke(): Flow<DataState<List<Joke>>>
}