package br.com.maschdy.moviez.data.mapper

import br.com.maschdy.moviez.data.entity.MovieEntity
import br.com.maschdy.moviez.data.entity.MoviesEntity
import br.com.maschdy.moviez.domain.model.Movie
import br.com.maschdy.moviez.domain.model.Movies

object Mapper {

    fun MovieEntity.toMovie(): Movie = Movie(
        isAdult = this.isAdult,
        backdropPath = this.backdropPath,
        genreIds = this.genreIds,
        id = this.id,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )

    fun MoviesEntity.toMovies(): Movies = Movies(
        this.page,
        this.results.map { it.toMovie() },
        this.totalPages,
        this.totalResults
    )
}
