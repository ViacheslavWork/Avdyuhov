package com.example.avdyuhov.ui.movies_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.avdyuhov.common.Result
import com.example.avdyuhov.domain.Repository
import com.example.avdyuhov.domain.model.MovieItem
import com.example.avdyuhov.ui.details.DetailsUi
import com.example.avdyuhov.ui.details.MovieDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Viacheslav Avd on 11.01.2023
 */


private const val TAG = "MoviesListViewModel"

@HiltViewModel
class MoviesListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val moviesPages: Flow<PagingData<MovieItemUi>> =
        repository.getPopularMovies().map { pagingData ->
            pagingData.map { MovieItemUi.fromMovieItem(it) }
        }.cachedIn(viewModelScope)

    val favouriteMovies: Flow<PagingData<MovieItemUi>> = repository.getFavoriteMovies()
        .map { movieItems ->
            PagingData.from(movieItems.map { MovieItemUi.fromMovieItem(it) })
        }

    fun setFavourite(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val movieResult = repository.getMovie(movieId)
            if (movieResult is Result.Success) {
                repository.setFavoriteMovie(movieResult.data)
            }
        }
    }
}



