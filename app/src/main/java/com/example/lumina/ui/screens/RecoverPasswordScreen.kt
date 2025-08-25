package com.example.lumina.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun RecoverPasswordScreen(navController: NavController, snackbarHostState: SnackbarHostState) {
    val scope = rememberCoroutineScope()
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Recuperar Contraseña", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Correo") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(16.dp))

        Button(onClick = {
            val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
            scope.launch {
                if (!email.matches(emailRegex)) snackbarHostState.showSnackbar("Error: Correo inválido")
                else {
                    snackbarHostState.showSnackbar("Si tu correo está registrado, recibirás las instrucciones de recuperación en $email")
                    navController.popBackStack()
                }
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Enviar instrucciones")
        }
    }
}
