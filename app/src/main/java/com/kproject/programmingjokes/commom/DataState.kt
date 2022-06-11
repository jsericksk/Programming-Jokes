package com.kproject.programmingjokes.commom

sealed class DataState<out T>(val result: T? = null) {
    object Loading : DataState<Nothing>()
    data class Success<T>(val data: T) : DataState<T>(result = data)
    data class Error<T>(val errorMessage: String? = null) : DataState<T>()
}