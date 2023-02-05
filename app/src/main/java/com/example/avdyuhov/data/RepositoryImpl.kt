package com.example.avdyuhov.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.avdyuhov.common.runCatchingResult
import com.example.avdyuhov.data.remote.RemoteDataSource
import com.example.avdyuhov.domain.Repository
import com.example.avdyuhov.domain.model.MovieDetails
import com.example.avdyuhov.domain.model.MovieItem
import com.example.avdyuhov.common.Result
import com.example.avdyuhov.data.local.MoviesDao
import com.example.avdyuhov.data.local.entity.MovieDetailsEntity
import com.example.avdyuhov.data.local.entity.toMovieDetails
import com.example.avdyuhov.data.local.entity.toMovieItem
import com.example.avdyuhov.data.remote.PopularMoviesPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Viacheslav Avd on 03.02.2023
 */
class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val topMoviesPagingSource: PopularMoviesPagingSource,
    private val moviesDao: MoviesDao
) :
    Repository {
    override fun getPopularMovies(): Flow<PagingData<MovieItem>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { topMoviesPagingSource }
        ).flow
    }

    override fun getFavoriteMovies(): Flow<List<MovieItem>> {
        return moviesDao.getFlowFavorites().map { it.map { entity -> entity.toMovieItem() } }
    }

    override suspend fun getMovie(id: Int): Result<MovieDetails> {
        return runCatchingResult {
            delay(3000)
            remoteDataSource.loadMovie(id)
        }
    }

    override suspend fun getFavoriteMovie(id: Int): Result<MovieDetails> {
        return runCatchingResult { moviesDao.getMovie(id).toMovieDetails() }
    }

    override suspend fun setFavoriteMovie(movie: MovieDetails): Unit = withContext(Dispatchers.IO) {
        moviesDao.insert(MovieDetailsEntity.fromMovieDetails(movie))
    }
}