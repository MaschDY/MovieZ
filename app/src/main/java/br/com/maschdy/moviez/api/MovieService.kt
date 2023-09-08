package br.com.maschdy.moviez.api

import br.com.maschdy.moviez.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET

interface MovieService {

    @GET("/3/movie/popular")
    suspend fun getMovies(): Response<MoviesResponse>
}
