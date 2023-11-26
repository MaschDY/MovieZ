package br.com.maschdy.moviez.presentation.movie_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.maschdy.moviez.domain.model.Genre
import br.com.maschdy.moviez.presentation.databinding.ItemGenreMovieBinding

class GenresMovieAdapter(
    private val genres: List<Genre>,
) : RecyclerView.Adapter<GenresMovieAdapter.GenreMovieViewHolder>() {

    inner class GenreMovieViewHolder(val binding: ItemGenreMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): GenreMovieViewHolder {
        val binding = ItemGenreMovieBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return GenreMovieViewHolder(binding)
    }

    override fun getItemCount(): Int = genres.size

    override fun onBindViewHolder(holder: GenreMovieViewHolder, position: Int) {
        val genre = genres[position]
        onBind(holder.binding, genre)
    }

    private fun onBind(
        binding: ItemGenreMovieBinding,
        genre: Genre
    ) = with(binding) {
        genreNameTextView.text = genre.name
    }
}
