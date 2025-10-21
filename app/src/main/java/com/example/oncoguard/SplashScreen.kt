package com.example.oncoguard

import android.window.SplashScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "Splash Screen", color = Color.Blue, fontSize = 32.sp)
    }

    // Após 2 segundos, navega para a Home
    LaunchedEffect(Unit) {
        delay(4000)
        navController.navigate(Screen.Inicio.route) {
            popUpTo(Screen.Splash.route) { inclusive = true }
        }
    }
}