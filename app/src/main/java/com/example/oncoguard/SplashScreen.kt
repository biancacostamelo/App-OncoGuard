package com.example.oncoguard

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFDAF3)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.group_17),
            contentDescription = "descrição",
            contentScale = ContentScale.Crop,
            modifier= Modifier
                .fillMaxWidth()
        )
    }

    // Após 2 segundos, navega para a Home
    LaunchedEffect(Unit) {
        delay(4000)
        navController.navigate(Screen.Inicio.route) {
            popUpTo(Screen.Splash.route) { inclusive = true }
        }
    }
}