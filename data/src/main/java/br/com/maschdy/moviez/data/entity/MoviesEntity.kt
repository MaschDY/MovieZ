package br.com.maschdy.moviez.data.entity

import com.squareup.moshi.Json

data class MoviesEntity(
    @Json(name = "page") val page: Int,
    @Json(name = "results") val results: List<MovieEntity>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int
)
