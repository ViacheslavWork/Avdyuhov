package com.example.avdyuhov.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.avdyuhov.R

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Top : Screen("top", R.string.top, Icons.Filled.List)
    object Favorites : Screen("favorites", R.string.favorites, Icons.Filled.Favorite)
}