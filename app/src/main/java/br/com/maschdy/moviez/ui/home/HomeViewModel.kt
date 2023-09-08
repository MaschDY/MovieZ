package br.com.maschdy.moviez.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.maschdy.moviez.api.MovieRemoteDataSource
import br.com.maschdy.moviez.model.ApiError
import br.com.maschdy.moviez.model.ApiException
import br.com.maschdy.moviez.model.ApiSuccess
import br.com.maschdy.moviez.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val movieRemote: MovieRemoteDataSource) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState.Success(emptyList()))
    val uiState: StateFlow<HomeUiState> get() = _uiState

    init {
        getMovies()
    }

    private fun getMovies() = viewModelScope.launch {
        when (val response = movieRemote.invoke()) {
            is ApiSuccess -> HomeUiState.Success(response.data.results)
            is ApiError -> HomeUiState.Error("${response.code} ${response.message}")
            is ApiException -> HomeUiState.Error("${response.e.message}")
        }
    }
}

sealed class HomeUiState {
    data class Success(val movies: List<Movie>) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}
