package br.com.maschdy.moviez.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import br.com.maschdy.moviez.domain.model.Movie
import br.com.maschdy.moviez.presentation.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel()
    private var popularMoviesAdapter: PopularMoviesAdapter =
        PopularMoviesAdapter(listOf(), ::navigateToMovieDetail)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureListeners()
    }

    override fun onResume() {
        super.onResume()
        popularMoviesAdapter.setCanClickItems(true)
    }

    private fun configureListeners() {
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    is HomeUiState.Error -> renderError(state.message)
                    is HomeUiState.Success -> renderSuccess(state.movies)
                    null -> {}
                }
            }
        }
    }

    private fun renderError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun renderSuccess(movies: List<Movie>) {
        setPopularMoviesRecyclerView(movies)
    }

    private fun setPopularMoviesRecyclerView(movies: List<Movie>) = with(binding) {
        popularMoviesAdapter = PopularMoviesAdapter(movies, ::navigateToMovieDetail)
        popularMoviesRecyclerView.adapter = popularMoviesAdapter
    }

    private fun navigateToMovieDetail(movieId: Int) {
        popularMoviesAdapter.setCanClickItems(false)
        HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(movieId = movieId)
            .apply(findNavController()::navigate)
    }
}
