package com.example.avdyuhov.ui

/**
 * Created by Viacheslav Avd on 11.01.2023
 */

sealed class MovieGuideDestination(val route: String) {
    object List : MovieGuideDestination("movies_list"){
        const val ARG_SCREEN = "ARG_SCREEN"
    }

    object Details : MovieGuideDestination("movie_details") {
        const val ARG_MOVIE_ID = "ARG_MOVIE_ID"
    }
}