package br.com.maschdy.moviez.domain.repository

import br.com.maschdy.moviez.domain.model.Movie
import br.com.maschdy.moviez.domain.model.Movies
import br.com.maschdy.moviez.domain.model.Result

interface MovieRepository {

    suspend fun getMovies(): Result<Movies>

    suspend fun getMovieById(id: Int): Result<Movie>
}
