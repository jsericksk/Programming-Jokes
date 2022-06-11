package com.kproject.programmingjokes.data.entity

import com.squareup.moshi.Json

data class JokeApiResponseEntity(
    @Json(name = "id") val id: Int,
    @Json(name = "type") val type: String,
    @Json(name = "setup") val setup: String,
    @Json(name = "punchline") val punchline: String
)