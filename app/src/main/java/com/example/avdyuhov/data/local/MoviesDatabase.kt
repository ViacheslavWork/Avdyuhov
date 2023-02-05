package com.example.avdyuhov.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.avdyuhov.data.local.converters.StringListConverter
import com.example.avdyuhov.data.local.entity.MovieDetailsEntity

@Database(
    entities = [MovieDetailsEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(StringListConverter::class)
abstract class MoviesDatabase : RoomDatabase() {
    abstract val moviesDao: MoviesDao
}