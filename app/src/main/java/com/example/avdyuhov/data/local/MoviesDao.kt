package com.example.avdyuhov.data.local

import androidx.room.*
import com.example.avdyuhov.data.local.entity.MovieDetailsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies")
    fun getFlowFavorites(): Flow<List<MovieDetailsEntity>>

    @Query("SELECT * FROM movies")
    suspend fun getFavorites(): List<MovieDetailsEntity>

    @Query("SELECT * FROM movies WHERE id=:id")
    fun getMovie(id: Int): MovieDetailsEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(film: MovieDetailsEntity): Long
}