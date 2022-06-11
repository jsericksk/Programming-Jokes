package com.kproject.programmingjokes.domain.usecase.database

import com.kproject.programmingjokes.domain.model.Joke

interface FavoriteExistsUseCase {
    suspend operator fun invoke(joke: Joke): Boolean
}