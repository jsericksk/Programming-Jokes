package com.kproject.programmingjokes.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kproject.programmingjokes.commom.DataState
import com.kproject.programmingjokes.domain.mapper.toDomain
import com.kproject.programmingjokes.domain.mapper.toUiState
import com.kproject.programmingjokes.domain.usecase.api.GetRandomJokesUseCase
import com.kproject.programmingjokes.domain.usecase.database.FavoriteExistsUseCase
import com.kproject.programmingjokes.domain.usecase.database.add.AddFavoriteUseCase
import com.kproject.programmingjokes.domain.usecase.database.delete.DeleteFavoriteUseCase
import com.kproject.programmingjokes.presentation.model.JokeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getRandomJokesUseCase: GetRandomJokesUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val favoriteExistsUseCase: FavoriteExistsUseCase
) : ViewModel() {
    private val _dataStateResult = MutableStateFlow<DataState<List<JokeUiState>>>(DataState.Loading)
    val dataStateResult: StateFlow<DataState<List<JokeUiState>>> = _dataStateResult

    init {
        getRandomJokes()
    }

    fun getRandomJokes() {
        viewModelScope.launch {
            _dataStateResult.value = DataState.Loading
            getRandomJokesUseCase().collect { dataState ->
                when (dataState) {
                    is DataState.Success -> {
                        val jokeList = dataState.data
                        _dataStateResult.value = DataState.Success(
                            jokeList.map { joke ->
                                val isFavorite = favoriteExistsUseCase(joke)
                                joke.toUiState(isFavorite)
                            }
                        )
                    }
                    is DataState.Error -> {
                        val errorMessage = dataState.errorMessage
                        _dataStateResult.value = DataState.Error(errorMessage)
                    }
                    else -> {}
                }
            }
        }
    }

    fun updateJokeList() {
        viewModelScope.launch {
            _dataStateResult.value.result?.let { jokeList ->
                val updatedJokeList = jokeList.map { jokeUiState ->
                    val isFavorite = favoriteExistsUseCase(jokeUiState.toDomain())
                    jokeUiState.copy(isFavorite = isFavorite)
                }
                _dataStateResult.value = DataState.Success(updatedJokeList)
            }
        }
    }

    fun addOrDeleteFavorite(jokeUiState: JokeUiState, itemPosition: Int) {
        viewModelScope.launch {
            val favoriteExists = favoriteExistsUseCase(jokeUiState.toDomain())
            if (favoriteExists) {
                deleteFavoriteUseCase(jokeUiState.toDomain())
            } else {
                addFavoriteUseCase(jokeUiState.toDomain())
            }
            val currentJokeList = _dataStateResult.value.result
            currentJokeList?.let { currentList ->
                val updatedJoke = currentList[itemPosition].copy(isFavorite = !favoriteExists)
                val updatedJokeList = currentList.toMutableList()
                updatedJokeList[itemPosition] = updatedJoke
                _dataStateResult.value = DataState.Success(updatedJokeList)
            }
        }
    }
}