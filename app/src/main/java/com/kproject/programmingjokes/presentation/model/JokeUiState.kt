package com.kproject.programmingjokes.presentation.model

data class JokeUiState(
    val id: Int,
    val setup: String,
    val punchline: String,
    var isFavorite: Boolean = false
)