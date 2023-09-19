package br.com.maschdy.moviez.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.maschdy.moviez.domain.model.Error
import br.com.maschdy.moviez.domain.model.Exception
import br.com.maschdy.moviez.domain.model.Movie
import br.com.maschdy.moviez.domain.model.Success
import br.com.maschdy.moviez.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val getMoviesUseCase: GetMoviesUseCase) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState?> = MutableStateFlow(null)
    val uiState: StateFlow<HomeUiState?> get() = _uiState

    init {
        getMovies()
    }

    private fun getMovies() = viewModelScope.launch {
        _uiState.value = when (val response = getMoviesUseCase.execute()) {
            is Success -> HomeUiState.Success(response.data.results)
            is Error -> HomeUiState.Error("${response.code} ${response.message}")
            is Exception -> HomeUiState.Error("${response.e.message}")
        }
    }
}

sealed class HomeUiState {
    data class Success(val movies: List<Movie>) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}
