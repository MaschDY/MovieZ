package br.com.maschdy.moviez.data.network.service

import br.com.maschdy.moviez.data.entity.MoviesEntity
import retrofit2.Response
import retrofit2.http.GET

interface MovieService {

    @GET("/3/movie/popular")
    suspend fun getMovies(): Response<MoviesEntity>
}
