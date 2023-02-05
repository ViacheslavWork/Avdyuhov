package com.example.avdyuhov.ui.movies_list

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.avdyuhov.R
import com.example.avdyuhov.ui.FailedScreen
import com.example.avdyuhov.ui.LoadingScreen
import com.example.avdyuhov.ui.theme.AvdyuhovTheme
import kotlinx.coroutines.flow.flowOf
import java.util.*

/**
 * Created by Viacheslav Avd on 09.01.2023
 */
private const val TAG = "MovieListScreen"

@Composable
fun MovieListScreen(
    onMovieClick: (id: Int) -> Unit = {},
    viewModel: MoviesListViewModel = hiltViewModel(),
    state: MovieListState = MovieListState.Popular,
) {
    val moviesPages = when (state) {
        MovieListState.Favourites -> viewModel.favouriteMovies.collectAsLazyPagingItems()
        MovieListState.Popular -> viewModel.moviesPages.collectAsLazyPagingItems()
    }
    Screen(
        moviesPages,
        onMovieClick = onMovieClick,
        onFavouriteClick = { viewModel.setFavourite(it) }
    )
}

@Composable
private fun HandleLoadState(loadState: LoadState) {
    when (loadState) {
        is LoadState.Error -> {
            Log.e(TAG, "MovieListScreen: Error")
            FailedScreen(R.string.unable_download_movies)
        }
        is LoadState.Loading -> { // Loading UI
            Log.d(TAG, "MovieListScreen: Loading")
            LoadingScreen()
        }
        is LoadState.NotLoading -> {}
    }
}

@Composable
private fun Screen(
    moviesPages: LazyPagingItems<MovieItemUi>,
    onMovieClick: (id: Int) -> Unit,
    onFavouriteClick: (id: Int) -> Unit = {}
) {
    AvdyuhovTheme {
        // A surface container using the 'background' color from the theme
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 14.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            ListBlock(onMovieClick = onMovieClick, onFavouriteClick = onFavouriteClick, moviesPages)
            HandleLoadState(loadState = moviesPages.loadState.refresh)
        }
    }
}

@Composable
fun ListBlock(
    onMovieClick: (id: Int) -> Unit,
    onFavouriteClick: (id: Int) -> Unit = {},
    moviesPages: LazyPagingItems<MovieItemUi>,
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxWidth(),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(bottom = 14.dp)
    ) {
        items(moviesPages.itemCount) { index ->
            moviesPages[index]?.let { movieItemUi ->
                MovieItem(
                    movie = movieItemUi,
                    onClick = { onMovieClick(movieItemUi.id) },
                    onFavouriteClick = onFavouriteClick
                )
            }
        }
        item {
            HandleLoadState(loadState = moviesPages.loadState.append)
        }
    }
}

@Composable
fun MovieItem(
    movie: MovieItemUi,
    onClick: (id: Int) -> Unit,
    onFavouriteClick: (id: Int) -> Unit = {}
) {
    AvdyuhovTheme {
        // A surface container using the 'background' color from the theme
        Column(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, MaterialTheme.colorScheme.onBackground, RoundedCornerShape(8.dp))
            .clickable { onClick(movie.id) }) {
            ImageBlock(movie = movie, onFavouriteClick = onFavouriteClick)
        }
    }
}

@Composable
private fun ImageBlock(movie: MovieItemUi, onFavouriteClick: (id: Int) -> Unit = {}) {
    Log.d("TAG", "ImageBlock: $movie")
    AvdyuhovTheme {
        // A surface container using the 'background' color from the theme
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            AsyncImage(
                model = movie.posterPath,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .graphicsLayer { alpha = 0.99f }
                    .drawWithContent {
                        val colors = listOf(Color.Black, Color.Transparent)
                        drawContent()
                        drawRect(
                            brush = Brush.verticalGradient(colors), blendMode = BlendMode.DstIn
                        )
                    },
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 10.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    HeadBlock(movie, onFavouriteClick = {
                        Log.d(TAG, "ImageBlock: OnFavouriteClick")
                        onFavouriteClick(movie.id)
                    })
                }
                Text(
                    text = movie.genres,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.tertiary
                )
                FilmRatingBlock(movie)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${movie.title} (${movie.year})",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun HeadBlock(movie: MovieItemUi, onFavouriteClick: () -> Unit = {}) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_like),
            contentDescription = null,
            modifier = Modifier
                .width(16.dp)
                .height(14.dp)
                .clickable { onFavouriteClick() },
            tint = if (movie.isLiked) MaterialTheme.colorScheme.tertiary
            else MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun FilmRatingBlock(movie: MovieItemUi) {
    Row(
        modifier = Modifier.padding(top = 8.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(5) {
            Icon(
                modifier = Modifier
                    .padding(end = 2.dp)
                    .size(8.dp),
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = null,
                tint =
                if (it <= movie.numberOfStars - 1) MaterialTheme.colorScheme.tertiary
                else MaterialTheme.colorScheme.onBackground
            )
        }
        Text(
            text = "${movie.numberOfReviews} reviews".uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.labelLarge,
            fontSize = 8.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun HeaderBlock() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = R.drawable.ic_target),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.tertiary
        )
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = stringResource(R.string.movies_list),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ImageBlockPreview() {
    ImageBlock(getTestMovies().first())
}

@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    HeaderBlock()
}

@Preview(showBackground = true)
@Composable
fun MoviesListPreview() {
    Screen(
        moviesPages = flowOf(PagingData.from(getTestMovies())).collectAsLazyPagingItems(),
        onMovieClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun MovieItemPreview() {
    MovieItem(getTestMovies().first(), onClick = {})
}

fun getTestMovies(): List<MovieItemUi> {
    val movies = mutableListOf<MovieItemUi>()
    repeat(6) {
        movies.add(
            MovieItemUi(
                id = it,
                title = "Avengers: End Game",
                genres = "Action, Adventure, Drama, Action, Adventure, Drama",
                numberOfStars = 4,
                numberOfReviews = 125,
                isLiked = it % 2 == 0,
                posterPath = "poster = R.drawable.im_avengers_end_game",
                year = "2002"
            )
        )
    }
    return movies
}

