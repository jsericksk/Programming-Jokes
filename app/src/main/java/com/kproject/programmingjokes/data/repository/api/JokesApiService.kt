package com.kproject.programmingjokes.data.repository.api

import com.kproject.programmingjokes.data.entity.JokeApiResponseEntity
import retrofit2.Response
import retrofit2.http.GET

interface JokesApiService {

    @GET("jokes/programming/ten")
    suspend fun getJokeList(): Response<List<JokeApiResponseEntity>>

    companion object {
        const val API_BASE_URL = "https://karljoke.herokuapp.com/"
    }
}