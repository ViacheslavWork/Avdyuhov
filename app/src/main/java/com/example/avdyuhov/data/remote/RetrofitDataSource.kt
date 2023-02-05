package com.example.avdyuhov.data.remote

import com.example.avdyuhov.domain.model.MovieDetails
import com.example.avdyuhov.domain.model.MovieItem
import javax.inject.Inject

/**
 * Created by Viacheslav Avd on 03.02.2023
 */
class RetrofitDataSource @Inject constructor(private val api: ApiService) : RemoteDataSource {
    override suspend fun loadMovies(): List<MovieItem> {
        return api.getPopularMovies().films.map { filmsItem ->
            MovieItem(
                id = filmsItem.filmId,
                title = filmsItem.nameRu ?: "",
                posterUrlPreview = filmsItem.posterUrlPreview ?: "",
                genres = filmsItem.genres.map { it.genre },
                rating = try {
                    filmsItem.rating.toFloat()
                } catch (e: java.lang.NumberFormatException) {
                    0f
                },
                numberOfReviews = filmsItem.ratingVoteCount,
                isLiked = false,
                year = filmsItem.year?.toInt() ?: 0
            )
        }
    }

    override suspend fun loadMovie(movieId: Int): MovieDetails {
        val movieDetails = api.getMovie(movieId)
        return MovieDetails(
            id = movieDetails.kinopoiskId,
            title = movieDetails.nameRu,
            countries = movieDetails.countries.map { it.country },
            genres = movieDetails.genres.map { it.genre },
            rating = movieDetails.ratingKinopoisk ?: 0f,
            ageLimit = movieDetails.ratingAgeLimits?.replace("age", "")?.toInt() ?: 0,
            numberOfReviews = movieDetails.ratingKinopoiskVoteCount ?: 0,
            storyLine = movieDetails.description ?: "",
            posterUrl = movieDetails.posterUrl ?: "",
            year = movieDetails.year ?: 0
        )
    }
}