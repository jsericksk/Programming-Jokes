package com.kproject.programmingjokes.domain.usecase.database.get

import com.kproject.programmingjokes.domain.model.Joke
import kotlinx.coroutines.flow.Flow

interface GetFavoritesUseCase {
    suspend operator fun invoke(): Flow<List<Joke>>
}