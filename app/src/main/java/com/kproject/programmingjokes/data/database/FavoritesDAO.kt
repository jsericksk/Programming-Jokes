package com.kproject.programmingjokes.data.database

import androidx.room.*
import com.kproject.programmingjokes.data.entity.FavoriteJokeEntity
import com.kproject.programmingjokes.data.utils.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDAO {

    @Query("SELECT * FROM ${Constants.FAVORITES_DATABASE_NAME}")
    fun getAllFavorites(): Flow<List<FavoriteJokeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: FavoriteJokeEntity)

    @Delete
    suspend fun deleteFavorite(favorite: FavoriteJokeEntity)

    @Query("SELECT COUNT() FROM ${Constants.FAVORITES_DATABASE_NAME} WHERE id = :id")
    suspend fun favoriteExists(id: Int): Int
}