package br.com.maschdy.moviez.presentation.home

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import br.com.maschdy.moviez.domain.model.Movie
import br.com.maschdy.moviez.presentation.R
import br.com.maschdy.moviez.presentation.databinding.ItemPopularMovieBinding
import br.com.maschdy.moviez.presentation.util.setSafeOnClickListener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

class PopularMoviesAdapter(
    private val movies: List<Movie>,
    private val onMovieClick: (movieId: Int) -> Unit
) : RecyclerView.Adapter<PopularMoviesAdapter.PopularMoviesViewHolder>() {

    private var canClickItems: Boolean = true

    fun setCanClickItems(canClickItems: Boolean) {
        this.canClickItems = canClickItems
    }

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
        binding.root.setSafeOnClickListener {
            if (!canClickItems) return@setSafeOnClickListener
            onMovieClick(movie.id)
        }

        titleTextView.text = movie.title

        val urlPoster = "https://image.tmdb.org/t/p/original" + movie.posterPath
        val posterPreview = R.drawable.ic_default_poster
        setPoster(
            url = urlPoster,
            imageView = posterImageView,
            defaultImagePreview = posterPreview,
            onLoadImageSuccess = { posterImage -> onLoadImageSuccess(binding, posterImage) },
            onLoadImageFailure = ::onLoadImageFailure
        )
    }

    private fun onLoadImageSuccess(
        binding: ItemPopularMovieBinding,
        posterImage: Drawable
    ) = with(binding) {
        posterImageView.setImageDrawable(posterImage)
        titleTextView.isVisible = false
    }

    private fun onLoadImageFailure(e: Exception?) {
        Log.e("GlideImage", Log.getStackTraceString(e))
    }

    private fun setPoster(
        url: String,
        imageView: ImageView,
        @DrawableRes defaultImagePreview: Int,
        onLoadImageSuccess: (Drawable) -> Unit = {},
        onLoadImageFailure: (Exception?) -> Unit = {}
    ) {
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transform(CenterInside(), RoundedCorners(16))
            .placeholder(defaultImagePreview)
        Glide.with(imageView)
            .load(url)
            .override(200, 300)
            .apply(requestOptions)
            .listener(configureImageRequestListener(onLoadImageSuccess, onLoadImageFailure))
            .into(imageView)
    }

    private fun configureImageRequestListener(
        onLoadImageSuccess: (Drawable) -> Unit,
        onLoadImageFailure: (Exception?) -> Unit
    ) = object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>,
            isFirstResource: Boolean
        ): Boolean {
            onLoadImageFailure(e)
            return true
        }

        override fun onResourceReady(
            resource: Drawable,
            model: Any,
            target: Target<Drawable>?,
            dataSource: DataSource,
            isFirstResource: Boolean
        ): Boolean {
            onLoadImageSuccess(resource)
            return true
        }
    }
}
