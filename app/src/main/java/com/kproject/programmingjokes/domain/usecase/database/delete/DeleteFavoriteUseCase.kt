package com.kproject.programmingjokes.domain.usecase.database.delete

import com.kproject.programmingjokes.domain.model.Joke

interface DeleteFavoriteUseCase {
    suspend operator fun invoke(joke: Joke)
}