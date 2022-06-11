package com.kproject.programmingjokes.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kproject.programmingjokes.domain.mapper.toDomain
import com.kproject.programmingjokes.domain.mapper.toUiState
import com.kproject.programmingjokes.domain.usecase.database.delete.DeleteFavoriteUseCase
import com.kproject.programmingjokes.domain.usecase.database.get.GetFavoritesUseCase
import com.kproject.programmingjokes.presentation.model.JokeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : ViewModel() {
    private val _favoriteList = MutableStateFlow<List<JokeUiState>>(emptyList())
    val favoriteList: StateFlow<List<JokeUiState>> = _favoriteList

    init {
        viewModelScope.launch {
            getFavoritesUseCase().map { favoriteListFromDomain ->
                favoriteListFromDomain.map { joke ->
                    joke.toUiState(isFavorite = true)
                }
            }.collect {
                _favoriteList.value = it
            }
        }
    }

    fun deleteFavorite(jokeUiState: JokeUiState) {
        viewModelScope.launch {
            deleteFavoriteUseCase(jokeUiState.toDomain())
        }
    }
}