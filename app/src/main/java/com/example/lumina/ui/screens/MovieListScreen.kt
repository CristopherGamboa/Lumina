package com.example.lumina.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lumina.data.MovieRepository

@Composable
fun MovieListScreen(navController: NavController, snackbarHostState: SnackbarHostState, modifier: Modifier) {
    val movies = MovieRepository.loadMovies()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        items(movies.size) { index ->
            val movie = movies[index]
            MovieItem(movie) {
                navController.navigate("movieDetail/${movie.id}")
            }
        }
    }
}