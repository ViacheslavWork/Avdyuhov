package com.example.avdyuhov.domain.model

/**
 * Created by Viacheslav Avd on 03.02.2023
 */
data class MovieDetails(
    val id: Int,
    val title: String,
    val countries: List<String>,
    val genres: List<String>,
    val rating: Float,
    val ageLimit: Int,
    val numberOfReviews: Int,
    val storyLine: String,
    val posterUrl: String,
    val year: Int,
)