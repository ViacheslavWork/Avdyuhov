package com.example.avdyuhov.data.remote.dto

import com.google.gson.annotations.SerializedName

data class DetailsResponse(

    @field:SerializedName("ratingImdb")
    val ratingImdb: Any? = null,

    @field:SerializedName("year")
    val year: Int? = null,

    @field:SerializedName("imdbId")
    val imdbId: String? = null,

    @field:SerializedName("filmLength")
    val filmLength: Int? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("reviewsCount")
    val reviewsCount: Int? = null,

    @field:SerializedName("ratingGoodReview")
    val ratingGoodReview: Any? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("endYear")
    val endYear: Any? = null,

    @field:SerializedName("ratingRfCriticsVoteCount")
    val ratingRfCriticsVoteCount: Int? = null,

    @field:SerializedName("hasImax")
    val hasImax: Boolean? = null,

    @field:SerializedName("nameRu")
    val nameRu: String,

    @field:SerializedName("lastSync")
    val lastSync: String? = null,

    @field:SerializedName("posterUrl")
    val posterUrl: String? = null,

    @field:SerializedName("genres")
    val genres: List<GenresItem>,

    @field:SerializedName("productionStatus")
    val productionStatus: Any? = null,

    @field:SerializedName("isTicketsAvailable")
    val isTicketsAvailable: Boolean? = null,

    @field:SerializedName("ratingMpaa")
    val ratingMpaa: String? = null,

    @field:SerializedName("ratingAgeLimits")
    val ratingAgeLimits: String? = null,

    @field:SerializedName("editorAnnotation")
    val editorAnnotation: Any? = null,

    @field:SerializedName("startYear")
    val startYear: Any? = null,

    @field:SerializedName("ratingKinopoiskVoteCount")
    val ratingKinopoiskVoteCount: Int? = null,

    @field:SerializedName("nameEn")
    val nameEn: Any? = null,

    @field:SerializedName("shortDescription")
    val shortDescription: Any? = null,

    @field:SerializedName("countries")
    val countries: List<CountriesItem>,

    @field:SerializedName("completed")
    val completed: Boolean? = null,

    @field:SerializedName("ratingAwaitCount")
    val ratingAwaitCount: Int? = null,

    @field:SerializedName("has3D")
    val has3D: Boolean? = null,

    @field:SerializedName("logoUrl")
    val logoUrl: Any? = null,

    @field:SerializedName("ratingKinopoisk")
    val ratingKinopoisk: Float?,

    @field:SerializedName("coverUrl")
    val coverUrl: Any? = null,

    @field:SerializedName("nameOriginal")
    val nameOriginal: String? = null,

    @field:SerializedName("ratingGoodReviewVoteCount")
    val ratingGoodReviewVoteCount: Int? = null,

    @field:SerializedName("serial")
    val serial: Boolean? = null,

    @field:SerializedName("webUrl")
    val webUrl: String? = null,

    @field:SerializedName("posterUrlPreview")
    val posterUrlPreview: String? = null,

    @field:SerializedName("shortFilm")
    val shortFilm: Boolean? = null,

    @field:SerializedName("ratingRfCritics")
    val ratingRfCritics: Any? = null,

    @field:SerializedName("ratingImdbVoteCount")
    val ratingImdbVoteCount: Int? = null,

    @field:SerializedName("ratingAwait")
    val ratingAwait: Any? = null,

    @field:SerializedName("ratingFilmCritics")
    val ratingFilmCritics: Any? = null,

    @field:SerializedName("slogan")
    val slogan: String? = null,

    @field:SerializedName("kinopoiskId")
    val kinopoiskId: Int,

    @field:SerializedName("ratingFilmCriticsVoteCount")
    val ratingFilmCriticsVoteCount: Int? = null
)
