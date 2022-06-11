package com.kproject.programmingjokes.data.repository.api

import com.kproject.programmingjokes.R
import com.kproject.programmingjokes.commom.DataState
import com.kproject.programmingjokes.domain.mapper.toDomain
import com.kproject.programmingjokes.domain.model.Joke
import com.kproject.programmingjokes.domain.provider.StringResourceProvider
import com.kproject.programmingjokes.domain.repository.api.JokesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class JokesRepositoryImpl(
    private val apiService: JokesApiService,
    private val stringResourceProvider: StringResourceProvider
) : JokesRepository {

    override suspend fun getRandomJokes(): Flow<DataState<List<Joke>>> = flow {
        try {
            val response = apiService.getJokeList()
            if (response.isSuccessful) {
                response.body()?.let { list ->
                    val jokeList = mutableListOf<Joke>()
                    list.forEach { jokeApiResponseEntity ->
                        jokeList.add(jokeApiResponseEntity.toDomain())
                    }
                    emit(DataState.Success(jokeList))
                }
            }
        } catch (e: Exception) {
            emit(DataState.Error(stringResourceProvider.getString(R.string.error_getting_joke_list)))
        }
    }
}