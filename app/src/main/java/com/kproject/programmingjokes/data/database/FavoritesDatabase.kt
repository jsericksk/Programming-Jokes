package com.kproject.programmingjokes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kproject.programmingjokes.data.entity.FavoriteJokeEntity

@Database(entities = [FavoriteJokeEntity::class], version = 1, exportSchema = false)
abstract class FavoritesDatabase : RoomDatabase() {

    abstract fun favoritesDAO(): FavoritesDAO
}