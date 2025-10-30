package com.example.oncoguard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun InicioScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Inicio Screen", color = Color.Magenta, fontSize = 45.sp)

        Button(onClick = { navController.navigate(Screen.Login.route) })
        { Text("Entrar") }

        Button(onClick = { navController.navigate(Screen.Cadastro.route) })
        { Text("Cadastro") }


        @Composable
        fun TextButtonExample(onClick: () -> Unit) {
            TextButton(
                onClick = { onClick() }
            ) {
                Text("Text Button")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun InicioPreview() {
    InicioScreen(
        navController = NavController(LocalContext.current) // TODO()
    )
}