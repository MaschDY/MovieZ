package br.com.maschdy.moviez.di

import br.com.maschdy.moviez.api.MovieRemoteDataSource
import br.com.maschdy.moviez.api.MovieService
import br.com.maschdy.moviez.api.util.createService
import br.com.maschdy.moviez.api.util.provideClient
import br.com.maschdy.moviez.api.util.provideMoshi
import br.com.maschdy.moviez.api.util.provideRetrofit
import br.com.maschdy.moviez.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import java.net.URL

val baseModules = module {

    single { provideMoshi() }
    single { provideClient() }
    single { provideRetrofit(URL("https://api.themoviedb.org"), get(), get()) }

    single<MovieService> { createService(get()) }
    single { MovieRemoteDataSource(get()) }

    viewModelOf(::HomeViewModel)
}
