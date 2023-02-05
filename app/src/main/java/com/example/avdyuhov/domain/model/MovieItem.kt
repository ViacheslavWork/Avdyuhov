package com.example.avdyuhov.domain.model

/**
 * Created by Viacheslav Avd on 03.02.2023
 */
data class MovieItem(
    val id: Int,
    val title: String,
    val posterUrlPreview: String,
    val genres: List<String>,
    val rating: Float,
    val numberOfReviews: Int,
    val isLiked: Boolean = false,
    val year: Int
)