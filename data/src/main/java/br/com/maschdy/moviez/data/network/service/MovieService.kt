package br.com.maschdy.moviez.data.network.service

import br.com.maschdy.moviez.data.entity.MovieEntity
import br.com.maschdy.moviez.data.entity.MoviesEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

    @GET("/3/movie/popular")
    suspend fun getMovies(): Response<MoviesEntity>

    @GET("/3/movie/{id}")
    suspend fun getMovieById(
        @Path("id") id: Int
    ): Response<MovieEntity>
}
