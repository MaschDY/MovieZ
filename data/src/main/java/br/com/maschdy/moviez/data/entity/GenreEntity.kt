package br.com.maschdy.moviez.data.entity

import com.squareup.moshi.Json

data class GenreEntity(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String
)
