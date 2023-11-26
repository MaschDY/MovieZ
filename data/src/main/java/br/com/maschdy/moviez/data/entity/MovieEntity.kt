package br.com.maschdy.moviez.data.entity

import com.squareup.moshi.Json

data class MovieEntity(
    @Json(name = "adult") val isAdult: Boolean,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "genres") val genres: List<GenreEntity>?,
    @Json(name = "id") val id: Int,
    @Json(name = "original_language") val originalLanguage: String,
    @Json(name = "original_title") val originalTitle: String,
    @Json(name = "overview") val overview: String,
    @Json(name = "popularity") val popularity: Double,
    @Json(name = "poster_path") val posterPath: String,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "title") val title: String,
    @Json(name = "video") val video: Boolean,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "vote_count") val voteCount: Int
)
