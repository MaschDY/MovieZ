package br.com.maschdy.moviez.data.datasource

import br.com.maschdy.moviez.data.entity.MoviesEntity
import br.com.maschdy.moviez.domain.model.Result

interface MovieDataSource {

    suspend fun getMovies(): Result<MoviesEntity>
}
