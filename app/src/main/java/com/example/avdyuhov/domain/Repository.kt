package com.example.avdyuhov.domain

import androidx.paging.PagingData
import com.example.avdyuhov.domain.model.MovieDetails
import com.example.avdyuhov.domain.model.MovieItem
import com.example.avdyuhov.common.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by Viacheslav Avd on 03.02.2023
 */
interface Repository {
    fun getPopularMovies(): Flow<PagingData<MovieItem>>
    fun getFavoriteMovies(): Flow<List<MovieItem>>
    suspend fun getMovie(id: Int): Result<MovieDetails>
    suspend fun getFavoriteMovie(id: Int): Result<MovieDetails>
    suspend fun setFavoriteMovie(movie: MovieDetails)
}