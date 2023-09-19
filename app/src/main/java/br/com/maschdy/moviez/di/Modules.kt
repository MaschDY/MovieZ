package br.com.maschdy.moviez.di

import br.com.maschdy.moviez.data.datasource.MovieDataSource
import br.com.maschdy.moviez.data.datasource.MovieDataSourceImpl
import br.com.maschdy.moviez.data.network.NetworkFactory.createService
import br.com.maschdy.moviez.data.network.NetworkFactory.provideClient
import br.com.maschdy.moviez.data.network.NetworkFactory.provideMoshi
import br.com.maschdy.moviez.data.network.NetworkFactory.provideRetrofit
import br.com.maschdy.moviez.data.network.service.MovieService
import br.com.maschdy.moviez.data.repository.MovieRepositoryImpl
import br.com.maschdy.moviez.domain.repository.MovieRepository
import br.com.maschdy.moviez.domain.usecase.GetMoviesUseCase
import br.com.maschdy.moviez.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import java.net.URL

val baseModules = module {

    single { provideMoshi() }
    single { provideClient() }
    single { provideRetrofit(URL("https://api.themoviedb.org"), get(), get()) }

    single<MovieService> { createService(get()) }
    single<MovieDataSource> { MovieDataSourceImpl(get()) }
    single<MovieRepository> { MovieRepositoryImpl(get()) }
    single { GetMoviesUseCase(get()) }

    viewModelOf(::HomeViewModel)
}
