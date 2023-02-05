package com.example.avdyuhov.ui.details

import com.example.avdyuhov.domain.model.MovieDetails
import com.example.avdyuhov.ui.toLine
import kotlin.math.roundToInt

/**
 * Created by Viacheslav Avd on 12.01.2023
 */
data class DetailsUi(
    val id: Int,
    val ageLimit: Int,
    val title: String,
    val genres: String,
    val numberOfStars: Int,
    val numberOfReviews: Int,
    val storyLine: String,
    val picturePath: String,
    val countries: String
) {
    companion object {
        fun fromMovieDetails(movieDetails: MovieDetails) =
            DetailsUi(
                id = movieDetails.id,
                ageLimit = movieDetails.ageLimit,
                title = movieDetails.title,
                genres = movieDetails.genres.toLine(),
                numberOfStars = (movieDetails.rating / 2).roundToInt(),
                numberOfReviews = movieDetails.numberOfReviews,
                picturePath = movieDetails.posterUrl,
                storyLine = movieDetails.storyLine,
                countries = movieDetails.countries.toLine()
            )
    }
}