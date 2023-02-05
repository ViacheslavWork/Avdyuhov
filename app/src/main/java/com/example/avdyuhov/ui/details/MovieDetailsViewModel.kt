package com.example.avdyuhov.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avdyuhov.domain.Repository
import com.example.avdyuhov.ui.details.MovieDetailsState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.avdyuhov.common.Result.*


/**
 * Created by Viacheslav Avd on 11.01.2023
 */

private const val TAG = "MovieDetailsViewModel"

@HiltViewModel
class MoviesDetailsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _movieDetailsState = MutableStateFlow<MovieDetailsState>(Loading)
    val movieDetailsState: StateFlow<MovieDetailsState> = _movieDetailsState.asStateFlow()

    fun getMovie(id: Int) {
//        Log.d(TAG, "getMovie: $id")
        viewModelScope.launch {
            val movie = repository.getMovie(id)
            _movieDetailsState.update {
                if (movie is Success) {
                    MovieLoaded(DetailsUi.fromMovieDetails(movie.data))
                } else
                    FailedToLoad
            }
        }
    }
}
