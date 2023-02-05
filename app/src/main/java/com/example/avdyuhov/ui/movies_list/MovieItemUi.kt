package com.example.avdyuhov.ui.movies_list

import com.example.avdyuhov.domain.model.MovieDetails
import com.example.avdyuhov.domain.model.MovieItem
import com.example.avdyuhov.ui.toLine
import kotlin.math.roundToInt

/**
 * Created by Viacheslav Avd on 09.01.2023
 */
data class MovieItemUi(
    val id: Int,
    val title: String,
    val genres: String = "",
    val year: String,
    val numberOfStars: Int,
    val numberOfReviews: Int,
    val isLiked: Boolean = false,
    val posterPath: String
) {
    companion object {
        fun fromMovieItem(movieItem: MovieItem): MovieItemUi {
            return MovieItemUi(
                id = movieItem.id,
                title = movieItem.title,
                numberOfStars = (movieItem.rating / 2).roundToInt(),
                numberOfReviews = movieItem.numberOfReviews,
                genres = movieItem.genres.toLine(),
                isLiked = false,
                posterPath = movieItem.posterUrlPreview,
                year = movieItem.year.toString()
            )
        }
    }
}

