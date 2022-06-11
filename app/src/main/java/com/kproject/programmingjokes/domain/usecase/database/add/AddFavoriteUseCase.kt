package com.kproject.programmingjokes.domain.usecase.database.add

import com.kproject.programmingjokes.domain.model.Joke

interface AddFavoriteUseCase {
    suspend operator fun invoke(joke: Joke)
}