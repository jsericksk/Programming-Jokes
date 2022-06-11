package com.kproject.programmingjokes.domain.mapper

import com.kproject.programmingjokes.data.entity.FavoriteJokeEntity
import com.kproject.programmingjokes.data.entity.JokeApiResponseEntity
import com.kproject.programmingjokes.domain.model.Joke
import com.kproject.programmingjokes.presentation.model.JokeUiState

// Data <---> Domain
fun Joke.fromDomain() = FavoriteJokeEntity(id, setup, punchline)

fun FavoriteJokeEntity.toDomain() = Joke(id, setup, punchline)

fun JokeApiResponseEntity.toDomain() = Joke(id, setup, punchline)

// Domain <---> Presentation
fun JokeUiState.toDomain() = Joke(id, setup, punchline)

fun Joke.toUiState(isFavorite: Boolean = false) = JokeUiState(id, setup, punchline, isFavorite)