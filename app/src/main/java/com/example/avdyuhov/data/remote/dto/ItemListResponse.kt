package com.example.avdyuhov.data.remote.dto

import com.example.avdyuhov.domain.model.MovieItem
import com.google.gson.annotations.SerializedName
import java.lang.NumberFormatException

data class ItemListResponse(

    @field:SerializedName("films")
    val films: List<FilmsItem>,

    @field:SerializedName("pagesCount")
    val pagesCount: Int? = null
)

data class FilmsItem(

    @field:SerializedName("nameRu")
    val nameRu: String? = null,

    @field:SerializedName("ratingVoteCount")
    val ratingVoteCount: Int,

    @field:SerializedName("posterUrl")
    val posterUrl: String? = null,

    @field:SerializedName("year")
    val year: String? = null,

    @field:SerializedName("genres")
    val genres: List<GenresItem>,

    @field:SerializedName("ratingChange")
    val ratingChange: Any? = null,

    @field:SerializedName("filmId")
    val filmId: Int,

    @field:SerializedName("filmLength")
    val filmLength: String? = null,

    @field:SerializedName("rating")
    val rating: String,

    @field:SerializedName("posterUrlPreview")
    val posterUrlPreview: String? = null,

    @field:SerializedName("nameEn")
    val nameEn: Any? = null,

    @field:SerializedName("countries")
    val countries: List<CountriesItem?>? = null
)

fun FilmsItem.toMovieItem() = MovieItem(
    id = filmId,
    title = nameRu ?: "",
    posterUrlPreview = posterUrlPreview ?: "",
    genres = genres.map { it.genre },
    rating = try {
        rating.toFloat()
    } catch (e: Exception) {
        0f
    },
    numberOfReviews = ratingVoteCount,
    isLiked = false,
    year = year?.toInt() ?: 0
)

