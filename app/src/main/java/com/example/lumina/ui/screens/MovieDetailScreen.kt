package com.example.lumina.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lumina.R
import com.example.lumina.data.MovieRepository
import com.example.lumina.ui.theme.Dimens

@Composable
fun MovieDetailScreen(navController: NavController, snackbarHostState: SnackbarHostState, modifier: Modifier, movieId: Int) {
    val movie = MovieRepository.loadMovies().find { it.id == movieId }

    if (movie != null) {
        Column(modifier = modifier.padding(16.dp)) {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(Modifier.height(8.dp))

            when (movie.genre) {
                "Sci-Fi" -> Text("🌌 Ciencia Ficción", color = Color.Cyan)
                "Action" -> Text("🔥 Acción", color = Color.Red)
                "Fantasy" -> Text("✨ Fantasía", color = Color.Magenta)
                else -> Text("🎬 ${movie.genre}")
            }

            Spacer(Modifier.height(16.dp))

            Image(
                painter = painterResource(id = movie.imageRes),
                contentDescription = "Imagen de película",
                modifier = Modifier
                    .size(300.dp)
                    .padding(bottom = Dimens.sectionSpacing)
            )

            Spacer(Modifier.height(16.dp))
            Text(movie.description)
        }
    } else {
        Text("Película no encontrada")
    }
}
