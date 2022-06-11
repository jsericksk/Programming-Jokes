package com.kproject.programmingjokes

import com.kproject.programmingjokes.data.repository.api.JokesApiService
import com.kproject.programmingjokes.data.repository.api.JokesRepositoryImpl
import com.kproject.programmingjokes.domain.repository.api.JokesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}