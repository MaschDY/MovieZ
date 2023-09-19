package br.com.maschdy.moviez.domain.repository

import br.com.maschdy.moviez.domain.model.Movies
import br.com.maschdy.moviez.domain.model.Result

interface MovieRepository {

    suspend fun getMovies(): Result<Movies>
}
