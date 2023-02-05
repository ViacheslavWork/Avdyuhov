package com.example.avdyuhov.ui.details

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.avdyuhov.R
import com.example.avdyuhov.ui.FailedScreen
import com.example.avdyuhov.ui.LoadingScreen
import com.example.avdyuhov.ui.details.MovieDetailsState.*
import com.example.avdyuhov.ui.theme.AvdyuhovTheme
import java.util.*

/**
 * Created by Viacheslav Avd on 09.01.2023
 */

private const val TAG = "DetailsScreen"

@Composable
fun DetailsScreen(
    movieId: Int,
    onBackButtonPressed: () -> Unit = {},
    viewModel: MoviesDetailsViewModel = hiltViewModel()
) {
    Log.d(TAG, "DetailsScreen movie id: $movieId")
    var isFirstRun by rememberSaveable { mutableStateOf(true) }
    if (isFirstRun.also { isFirstRun = false }) {
        viewModel.getMovie(movieId)
    }
    val movieDetailsState by viewModel.movieDetailsState.collectAsState()

    when (movieDetailsState) {
        is MovieLoaded -> {
            Screen(
                (movieDetailsState as MovieLoaded).movieDetailsUi,
                onBackButtonPressed
            )
        }
        is FailedToLoad -> FailedScreen(R.string.unable_download_movie_details)
        is Loading -> LoadingScreen()
    }
}


@Composable
private fun Screen(
    movieDetails: DetailsUi,
    onBackButtonPressed: () -> Unit = {},
) {
    AvdyuhovTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.background)
        ) {
            AsyncImage(
                model = movieDetails.picturePath,
                modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer { alpha = 0.99f }
                    .drawWithContent {
                        val colors = listOf(
                            Color.Black, Color.Transparent
                        )
                        drawContent()
                        drawRect(
                            brush = Brush.verticalGradient(colors), blendMode = BlendMode.DstIn
                        )
                    },
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
            )
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Spacer(modifier = Modifier.height(59.dp))
                BackButton(onBackButtonPressed)
                Spacer(modifier = Modifier.height(40.dp))
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = stringResource(id = R.string.age_limit, movieDetails.ageLimit),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = movieDetails.title,
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = movieDetails.genres,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.tertiary
                )
                FilmRatingBlock(movieDetails)
                Text(
                    text = movieDetails.countries,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary
                )
                StoryLineBlock(movieDetails.storyLine)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun BackButton(onBackButtonPressed: () -> Unit = {}) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable(onClick = onBackButtonPressed)
    ) {

        Icon(
            imageVector = Icons.Filled.KeyboardArrowLeft,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = stringResource(id = R.string.back_button_text),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 2.dp),
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
private fun FilmRatingBlock(movie: DetailsUi) {
    Row(
        modifier = Modifier.padding(top = 8.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(5) {
            Icon(
                modifier = Modifier.padding(end = 4.dp),
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = null,
                tint = if (it <= movie.numberOfStars - 1) MaterialTheme.colorScheme.tertiary
                else MaterialTheme.colorScheme.onBackground
            )
        }
        Text(
            text = "${movie.numberOfReviews} reviews".uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun StoryLineBlock(storyLine: String) {
    Column(modifier = Modifier.padding(top = 24.dp)) {
        Text(
            text = stringResource(R.string.storyline),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = storyLine,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BackButtonPreview() {
    BackButton()
}

@Preview(showBackground = true)
@Composable
fun RatingBlockPreview() {
    FilmRatingBlock(
        createDetailsUi()
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF191926)
@Composable
fun ScreenPreview() {
    AvdyuhovTheme {
        Screen(createDetailsUi())
    }
}

@Composable
private fun createDetailsUi() = DetailsUi(
    id = 1,
    ageLimit = 13,
    title = "Avengers: End Game",
    genres = "Action, Adventure, Fantasy",
    numberOfStars = 4,
    numberOfReviews = 100500,
    picturePath = "",
    storyLine = "The Red Ribbon Army, an evil organization that was once destroyed by Goku in the past, has been reformed by a group of people who have created new and mightier Androids, Gamma 1 and Gamma 2, and seek vengeance against Goku and his family.",
    countries = "China, USA"
)
