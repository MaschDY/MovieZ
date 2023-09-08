package br.com.maschdy.moviez.api

import br.com.maschdy.moviez.api.util.handleApi
import br.com.maschdy.moviez.model.ApiResult
import br.com.maschdy.moviez.model.MoviesResponse

class MovieRemoteDataSource(private val movieService: MovieService) {

    suspend operator fun invoke(): ApiResult<MoviesResponse> = handleApi { movieService.getMovies() }
}
