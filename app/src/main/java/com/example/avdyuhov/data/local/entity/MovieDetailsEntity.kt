package com.example.avdyuhov.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.avdyuhov.data.local.converters.StringListConverter
import com.example.avdyuhov.domain.model.MovieDetails
import com.example.avdyuhov.domain.model.MovieItem

/**
 * Created by Viacheslav Avd on 05.02.2023
 */
@Entity(tableName = "movies")
data class MovieDetailsEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    @TypeConverters(StringListConverter::class)
    val countries: List<String>,
    @TypeConverters(StringListConverter::class)
    val genres: List<String>,
    val rating: Float,
    val ageLimit: Int,
    val numberOfReviews: Int,
    val storyLine: String,
    val posterUrl: String,
    val year: Int
) {
    companion object {
        fun fromMovieDetails(movieDetails: MovieDetails) = MovieDetailsEntity(
            id = movieDetails.id,
            title = movieDetails.title,
            countries = movieDetails.countries,
            genres = movieDetails.genres,
            rating = movieDetails.rating,
            ageLimit = movieDetails.ageLimit,
            numberOfReviews = movieDetails.numberOfReviews,
            storyLine = movieDetails.storyLine,
            posterUrl = movieDetails.posterUrl,
            year = movieDetails.year
        )
    }
}

fun MovieDetailsEntity.toMovieDetails() =
    MovieDetails(
        id = id,
        title = title,
        countries = countries,
        genres = genres,
        rating = rating,
        ageLimit = ageLimit,
        numberOfReviews = numberOfReviews,
        storyLine = storyLine,
        posterUrl = posterUrl,
        year = year
    )

fun MovieDetailsEntity.toMovieItem() =
    MovieItem(
        id = id,
        title = title,
        genres = genres,
        rating = rating,
        numberOfReviews = numberOfReviews,
        posterUrlPreview = posterUrl,
        year = year
    )
