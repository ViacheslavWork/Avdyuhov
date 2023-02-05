package com.example.avdyuhov.ui.movies_list

/**
 * Created by Viacheslav Avd on 05.02.2023
 */
sealed class MovieListState {
    object Popular : MovieListState()
    object Favourites : MovieListState()
}
