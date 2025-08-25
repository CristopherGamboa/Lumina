package com.example.lumina.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lumina.data.FakeUserRepository
import com.example.lumina.navigation.Screen
import kotlinx.coroutines.launch
import com.example.lumina.R
import com.example.lumina.ui.theme.Dimens

@Composable
fun LoginScreen(navController: NavController, snackbarHostState: SnackbarHostState) {
    val scope = rememberCoroutineScope()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.lumina),
            contentDescription = "Logo de Lúmina",
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = Dimens.sectionSpacing)
        )

        Text("Iniciar Sesión", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = email, onValueChange = { email = it },
            label = { Text("Correo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                val user = FakeUserRepository.users.find { it.email == email && it.password == password }
                scope.launch {
                    if (user != null) snackbarHostState.showSnackbar("Bienvenido $email")
                    else snackbarHostState.showSnackbar("Error: credenciales inválidas")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Entrar") }

        Spacer(Modifier.height(8.dp))
        TextButton(onClick = { navController.navigate(Screen.Recover.route) }) { Text("¿Olvidaste tu contraseña?") }
        TextButton(onClick = { navController.navigate(Screen.Register.route) }) { Text("Crear cuenta") }
    }
}
