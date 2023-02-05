package com.example.avdyuhov.ui.details

/**
 * Created by Viacheslav Avd on 29.01.2023
 */
sealed class MovieDetailsState {
    data class MovieLoaded(val movieDetailsUi: DetailsUi) : MovieDetailsState()
    object FailedToLoad : MovieDetailsState()
    object Loading : MovieDetailsState()
}