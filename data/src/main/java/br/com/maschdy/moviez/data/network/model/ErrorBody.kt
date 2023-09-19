package br.com.maschdy.moviez.data.network.model

import com.squareup.moshi.Json

data class ErrorBody(
    @Json(name = "success") val isSuccess: Boolean,
    @Json(name = "status_code") val statusCode: Int,
    @Json(name = "status_message") val statusMessage: String
)
