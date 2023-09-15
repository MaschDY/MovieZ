package br.com.maschdy.moviez.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.maschdy.moviez.R
import br.com.maschdy.moviez.databinding.ItemPopularMovieBinding
import br.com.maschdy.moviez.model.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class PopularMoviesAdapter(
    private val movies: List<Movie>,
    private val onMovieClick: (movieId: Int) -> Unit
) :
    RecyclerView.Adapter<PopularMoviesAdapter.PopularMoviesViewHolder>() {

    inner class PopularMoviesViewHolder(val binding: ItemPopularMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        val binding = ItemPopularMovieBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return PopularMoviesViewHolder(binding)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        val movie = movies[position]
        onBind(holder.binding, movie)
    }

    private fun onBind(
        binding: ItemPopularMovieBinding,
        movie: Movie
    ) = with(binding) {
        binding.root.setOnClickListener {
            onMovieClick(movie.id)
        }

        val urlPoster = "https://image.tmdb.org/t/p/original" + movie.posterPath
        setPoster(urlPoster, posterImageView)

        titleTextView.text = movie.title
    }

    private fun setPoster(url: String, imageView: ImageView) {
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transform(CenterInside(), RoundedCorners(16))
            .placeholder(R.drawable.ic_default_poster)
        Glide.with(imageView)
            .load(url)
            .override(200, 300)
            .apply(requestOptions)
            .into(imageView)
    }
}
