package br.com.maschdy.moviez.presentation.movie_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.maschdy.moviez.domain.model.Error
import br.com.maschdy.moviez.domain.model.Exception
import br.com.maschdy.moviez.domain.model.Movie
import br.com.maschdy.moviez.domain.model.Success
import br.com.maschdy.moviez.domain.usecase.GetMovieByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    stateHandle: SavedStateHandle,
    private val getMovieByIdUseCase: GetMovieByIdUseCase
) : ViewModel() {

    private val movieId: Int = stateHandle["movie_id"] ?: 0

    private val _uiState: MutableStateFlow<MovieDetailUiState?> = MutableStateFlow(null)
    val uiState: StateFlow<MovieDetailUiState?> get() = _uiState

    fun getMovie() = viewModelScope.launch {
        _uiState.value = when (val response = getMovieByIdUseCase.execute(movieId)) {
            is Success -> MovieDetailUiState.Success(response.data)
            is Error -> MovieDetailUiState.Error("${response.code} ${response.message}")
            is Exception -> MovieDetailUiState.Error("${response.e.message}")
        }
    }
}

sealed class MovieDetailUiState {
    data class Success(val movie: Movie) : MovieDetailUiState()
    data class Error(val message: String) : MovieDetailUiState()
}
