package com.example.lumina.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lumina.data.FakeUserRepository
import com.example.lumina.data.User
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(navController: NavController, snackbarHostState: SnackbarHostState) {
    val scope = rememberCoroutineScope()
    var name by remember { mutableStateOf("") }
    var birthdate by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("Chile") }
    var expanded by remember { mutableStateOf(false) }
    val countries = listOf("Chile", "Argentina", "España")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Registro", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Nombre completo") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = birthdate, onValueChange = { birthdate = it }, label = { Text("Fecha de nacimiento") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Correo") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Contraseña") }, modifier = Modifier.fillMaxWidth(), visualTransformation = PasswordVisualTransformation())
        Spacer(Modifier.height(8.dp))

        // Selector país
        Box {
            OutlinedTextField(value = country, onValueChange = {}, label = { Text("País") }, modifier = Modifier.fillMaxWidth(), readOnly = true)
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                countries.forEach {
                    DropdownMenuItem(text = { Text(it) }, onClick = { country = it; expanded = false })
                }
            }
            Spacer(modifier = Modifier
                .matchParentSize()
                .clickable { expanded = true })
        }

        Spacer(Modifier.height(16.dp))

        Button(onClick = {
            val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
            scope.launch {
                if (!email.matches(emailRegex)) snackbarHostState.showSnackbar("Error: Correo inválido")
                else {
                    FakeUserRepository.users.add(User(email, password))
                    snackbarHostState.showSnackbar("Usuario registrado correctamente")
                    navController.popBackStack()
                }
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Registrarse")
        }
    }
}
