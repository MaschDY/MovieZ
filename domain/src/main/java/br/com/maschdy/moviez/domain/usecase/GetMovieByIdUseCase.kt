package br.com.maschdy.moviez.domain.usecase

import br.com.maschdy.moviez.domain.model.Movie
import br.com.maschdy.moviez.domain.model.Result
import br.com.maschdy.moviez.domain.repository.MovieRepository

class GetMovieByIdUseCase(private val movieRepository: MovieRepository) {

    suspend fun execute(id: Int): Result<Movie> {
        return movieRepository.getMovieById(id)
    }
}
