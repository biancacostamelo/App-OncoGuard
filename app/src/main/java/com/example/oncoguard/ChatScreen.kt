package com.example.oncoguard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun ChatScreen(navController: NavController) {

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2A1D9))
    )

    { Text("Como posso ajudar?")
        Image(painter = painterResource(id=R.drawable.aliciachat), contentDescription = "Foto da nossa IA")
    }

}

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    ChatScreen(
        navController = NavController(LocalContext.current) // TODO()
    )
}