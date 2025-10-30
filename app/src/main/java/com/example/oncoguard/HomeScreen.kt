package com.example.oncoguard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.oncoguard.ui.theme.OncoGuardTheme

@Composable
fun HomeScreen(navController: NavController){

    Scaffold(
        bottomBar = { CustomBottomBar(navController = NavController(LocalContext.current)) }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)

        ) {
            Text(text = "Home Screen", color = Color.Yellow, fontSize = 40.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navController = NavController(LocalContext.current) // TODO()
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview2() {
    OncoGuardTheme {
        HomeScreen(
            navController = NavController(LocalContext.current) // TODO()
        )
    }
}
