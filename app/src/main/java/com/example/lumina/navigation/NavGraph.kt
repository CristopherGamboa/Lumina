package com.example.lumina.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lumina.ui.screens.LoginScreen
import com.example.lumina.ui.screens.RecoverPasswordScreen
import com.example.lumina.ui.screens.RegisterScreen

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Recover : Screen("recover")
}

@Composable
fun AppNavHost(navController: NavHostController, snackbarHostState: SnackbarHostState) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) { LoginScreen(navController, snackbarHostState) }
        composable(Screen.Register.route) { RegisterScreen(navController, snackbarHostState) }
        composable(Screen.Recover.route) { RecoverPasswordScreen(navController, snackbarHostState) }
    }
}
