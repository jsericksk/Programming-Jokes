package com.kproject.programmingjokes.di

import android.app.Application
import androidx.room.Room
import com.kproject.programmingjokes.data.utils.Constants
import com.kproject.programmingjokes.data.database.FavoritesDatabase
import com.kproject.programmingjokes.data.repository.api.JokesApiService
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val databaseModule = module {
    // Database
    single { provideDatabase(androidApplication()) }
    // DAO
    single { get<FavoritesDatabase>().favoritesDAO() }
}

val networkModule = module {
    single { createApiService() }
}

private fun provideDatabase(application: Application): FavoritesDatabase {
    return Room.databaseBuilder(
        application,
        FavoritesDatabase::class.java,
        Constants.FAVORITES_DATABASE_NAME
    ).build()
}

private fun createApiService(): JokesApiService {
    val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(JokesApiService.API_BASE_URL)
        .build()
    return retrofit.create(JokesApiService::class.java)
}