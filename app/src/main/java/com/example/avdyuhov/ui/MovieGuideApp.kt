package com.example.avdyuhov.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.avdyuhov.ui.MovieGuideDestination.Details.ARG_MOVIE_ID
import com.example.avdyuhov.ui.MovieGuideDestination.List.ARG_SCREEN
import com.example.avdyuhov.ui.details.DetailsScreen
import com.example.avdyuhov.ui.movies_list.MovieListScreen
import com.example.avdyuhov.ui.movies_list.MovieListState
import com.example.avdyuhov.ui.movies_list.MoviesListViewModel

private const val TAG = "MovieGuideApp"

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MovieGuideApp() {
    val navController = rememberNavController()
    val items = listOf(
        Screen.Top,
        Screen.Favorites,
    )
    val viewModel: MoviesListViewModel = hiltViewModel()
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(stringResource(screen.resourceId)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            Log.d(TAG, "HiltViewModle: $viewModel")
                            navController.navigate(route = "${MovieGuideDestination.List.route}/${screen.route}")
                        }
                    )
                }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = "${MovieGuideDestination.List.route}/{$ARG_SCREEN}",
            modifier = Modifier
        ) {
            composable(
                route = "${MovieGuideDestination.List.route}/{$ARG_SCREEN}",
                arguments = listOf(navArgument(ARG_SCREEN) { type = NavType.StringType })
            ) {
                val movieListState = when (it.arguments?.getString(ARG_SCREEN)) {
                    Screen.Top.route -> MovieListState.Popular
                    Screen.Favorites.route -> MovieListState.Favourites
                    else -> MovieListState.Popular
                }
                MovieListScreen(
                    state = movieListState,
                    onMovieClick = { movieId ->
                        navController.navigate(route = "${MovieGuideDestination.Details.route}/$movieId")
                    })
            }
            composable(
                route = "${MovieGuideDestination.Details.route}/{$ARG_MOVIE_ID}",
                arguments = listOf(navArgument(ARG_MOVIE_ID) { type = NavType.IntType })
            ) { navBackStackEntry ->
                val movieId = navBackStackEntry.arguments?.getInt(ARG_MOVIE_ID)
                movieId?.let { id ->
                    DetailsScreen(
                        movieId = id,
                        onBackButtonPressed = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}