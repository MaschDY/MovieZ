package br.com.maschdy.moviez.data.datasource

import br.com.maschdy.moviez.data.entity.MovieEntity
import br.com.maschdy.moviez.data.entity.MoviesEntity
import br.com.maschdy.moviez.data.network.NetworkHandler
import br.com.maschdy.moviez.data.network.service.MovieService
import br.com.maschdy.moviez.domain.model.Result

class MovieDataSourceImpl(private val movieService: MovieService) : MovieDataSource {

    override suspend fun getMovies(): Result<MoviesEntity> =
        NetworkHandler.handle { movieService.getMovies() }

    override suspend fun getMovieById(id: Int): Result<MovieEntity> =
        NetworkHandler.handle { movieService.getMovieById(id) }
}
