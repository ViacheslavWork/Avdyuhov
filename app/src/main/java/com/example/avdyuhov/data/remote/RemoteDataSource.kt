package com.example.avdyuhov.data.remote

import com.example.avdyuhov.domain.model.MovieDetails
import com.example.avdyuhov.domain.model.MovieItem

interface RemoteDataSource {
    suspend fun loadMovies(): List<MovieItem>
    suspend fun loadMovie(movieId: Int): MovieDetails
}