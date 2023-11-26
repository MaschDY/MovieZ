package br.com.maschdy.moviez.presentation.movie_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import br.com.maschdy.moviez.domain.model.Genre
import br.com.maschdy.moviez.domain.model.Movie
import br.com.maschdy.moviez.presentation.R
import br.com.maschdy.moviez.presentation.databinding.FragmentMovieDetailBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding
    private val viewModel: MovieDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovie()
        binding.arrowBackClickArea.setOnClickListener { findNavController().navigateUp() }
        configureListeners()
    }

    private fun configureListeners() {
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    is MovieDetailUiState.Error -> renderError(state.message)
                    is MovieDetailUiState.Success -> renderSuccess(state.movie)
                    null -> {}
                }
            }
        }
    }

    private fun renderError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun renderSuccess(movie: Movie) {
        setPoster(movie.backdropPath)
        setMovieInfo(movie)
        setMovieGenres(movie.genres ?: listOf())
    }

    private fun setPoster(backdropPosterPath: String) = with(binding) {
        val url = "https://image.tmdb.org/t/p/original$backdropPosterPath"
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transform(CenterCrop())
            .placeholder(R.drawable.ic_default_poster)
        Glide.with(moviePosterImageView)
            .load(url)
            .apply(requestOptions)
            .into(moviePosterImageView)
    }

    private fun setMovieInfo(movie: Movie) = with(binding) {
        movieTitleTextView.text = movie.title
        movieDescriptionTextView.text = movie.overview
    }

    private fun setMovieGenres(genres: List<Genre>) = with(binding) {
        if (genres.isEmpty()) {
            genresRecyclerView.isVisible = false
        } else {
            genresRecyclerView.adapter = GenresMovieAdapter(genres)
        }
    }
}
