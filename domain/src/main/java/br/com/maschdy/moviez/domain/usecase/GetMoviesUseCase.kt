package br.com.maschdy.moviez.domain.usecase

import br.com.maschdy.moviez.domain.model.Movies
import br.com.maschdy.moviez.domain.model.Result
import br.com.maschdy.moviez.domain.repository.MovieRepository

class GetMoviesUseCase(private val movieRepository: MovieRepository) {

    suspend fun execute(): Result<Movies> {
        return movieRepository.getMovies()
    }
}
