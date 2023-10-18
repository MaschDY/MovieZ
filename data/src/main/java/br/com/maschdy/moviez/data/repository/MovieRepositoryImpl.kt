package br.com.maschdy.moviez.data.repository

import br.com.maschdy.moviez.data.datasource.MovieDataSource
import br.com.maschdy.moviez.data.mapper.Mapper.toMovie
import br.com.maschdy.moviez.data.mapper.Mapper.toMovies
import br.com.maschdy.moviez.domain.model.Movie
import br.com.maschdy.moviez.domain.model.Movies
import br.com.maschdy.moviez.domain.model.Result
import br.com.maschdy.moviez.domain.model.Success
import br.com.maschdy.moviez.domain.repository.MovieRepository

class MovieRepositoryImpl(private val movieDataSource: MovieDataSource) : MovieRepository {

    override suspend fun getMovies(): Result<Movies> {
        val response = movieDataSource.getMovies()
        return if (response is Success) {
            Success(response.data.toMovies())
        } else {
            response as Result<Movies>
        }
    }

    override suspend fun getMovieById(id: Int): Result<Movie> {
        val response = movieDataSource.getMovieById(id)
        return if (response is Success) {
            Success(response.data.toMovie())
        } else {
            response as Result<Movie>
        }
    }
}
