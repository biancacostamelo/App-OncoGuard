package com.example.oncoguard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController

@Composable
fun CustomBottomBar(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(Color.Transparent),
        contentAlignment = Alignment.BottomCenter
    ) {
        // Fundo azul arredondado
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            color = Color(0xFF54A1E0),
            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
        ) {}

        // Ícones + avatar central
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 60.dp)
                .height(68.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* ação home */ }) {
                Icon(Icons.Filled.Home, "Localized description", tint = Color(0xFFB60158))
            }

            IconButton(onClick = { /* ação adicionar */ }) {
                Icon(Icons.Filled.Add, "Localized description", tint = Color(0xFFB60158))
            }
        }

        // Avatar central sobreposto
        Box(
            modifier = Modifier
                .offset(y = (-20).dp)
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFFB4005E))
                .clickable {
                    navController.navigate(Screen.Cadastro.route)
                },
                contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(63.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFBE3F4)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.iconnavbar), // sua imagem
                    contentDescription = "Avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    CustomBottomBar(
        navController = NavController(LocalContext.current) // TODO()
    )
}