package com.example.oncoguard

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun PlanosScreen(navController: NavController){
    Scaffold(
        bottomBar = { CustomBottomBar(navController = navController) }
    ) { paddingValues ->

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)

        ) {
            Text(text = "Tela de Planos", color = Color.Yellow, fontSize = 40.sp)

            IconButton(onClick = { navController.navigate(Screen.ConfigScreen.route) }) {
                Icon(Icons.Filled.AccountCircle, "Localized description", tint = Color(0xFFB60158))
            }
        }
    }
}