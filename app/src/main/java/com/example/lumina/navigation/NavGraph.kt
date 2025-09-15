package com.example.lumina.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lumina.ui.screens.LoginScreen
import com.example.lumina.ui.screens.MovieDetailScreen
import com.example.lumina.ui.screens.MovieListScreen
import com.example.lumina.ui.screens.RecoverPasswordScreen
import com.example.lumina.ui.screens.RegisterScreen

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Recover : Screen("recover")
    object MovieList : Screen("movieList")
    object MovieDetail : Screen("movieDetail/{movieId}")
}

@Composable
fun AppNavHost(navController: NavHostController, snackbarHostState: SnackbarHostState, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) { LoginScreen(navController, snackbarHostState, modifier) }
        composable(Screen.Register.route) { RegisterScreen(navController, snackbarHostState) }
        composable(Screen.Recover.route) { RecoverPasswordScreen(navController, snackbarHostState) }
        composable(Screen.MovieList.route) { MovieListScreen(navController, snackbarHostState, modifier) }
        composable(Screen.MovieDetail.route) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull()
            movieId?.let {
                MovieDetailScreen(navController, snackbarHostState, modifier, it)
            }
        }


    }
}
