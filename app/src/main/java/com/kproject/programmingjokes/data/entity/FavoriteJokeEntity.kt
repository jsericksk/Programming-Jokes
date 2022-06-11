package com.kproject.programmingjokes.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites_database")
data class FavoriteJokeEntity(
    @PrimaryKey val id: Int,
    val setup: String,
    val punchline: String
)