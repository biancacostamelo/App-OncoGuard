package com.example.oncoguard.core.components

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
import androidx.navigation.NavController
import com.example.oncoguard.R
import com.example.oncoguard.core.navigation.Screen

@Composable
fun CustomBottomBar(navController: NavController) {
    // pega o padding do navigation bar (pode ser 0 em gesture nav)
    val navBarBottomPadding = WindowInsets.navigationBars
        .asPaddingValues()
        .calculateBottomPadding()

    // altura visível da barra "azul" (a parte arredondada)
    val visibleBarHeight = 60.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            // deixe espaço para a area da nav bar (evita sobrepor os botões)
            .height(visibleBarHeight + navBarBottomPadding),
        contentAlignment = Alignment.BottomCenter
    ) {
        // Fundo azul que também cobre a área do navigation bar (evita o branco)
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                // Estende o Surface para cobrir a nav bar somando a padding
                .height(visibleBarHeight + navBarBottomPadding),
            color = Color(0xFF54A1E0),
            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
            tonalElevation = 4.dp
        ) {
            // opcional: dentro do Surface você pode colocar um Box para separar a área "visível" (sem o bottom inset)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = navBarBottomPadding) // empurra conteúdo pra cima, mantendo o azul abaixo
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(visibleBarHeight)
                        .padding(horizontal = 60.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { navController.navigate(Screen.Home.route) }) {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Home",
                            tint = Color(0xFFB60158),
                            modifier = Modifier.size(30.dp)
                        )
                    }

                    IconButton(onClick = { navController.navigate(Screen.Planos.route) }) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Adicionar",
                            tint = Color(0xFFB60158),
                            modifier = Modifier.size(35.dp)
                        )
                    }
                }
            }
        }

        // Avatar central sobreposto (offset negativo para ficar "flutuando" sobre a parte azul)
        Box(
            modifier = Modifier
                .offset(y = (-65).dp)
                .size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFFB4005E))
                .clickable { navController.navigate(Screen.ChatScreen.route) },
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
                    painter = painterResource(R.drawable.iconnavbar),
                    contentDescription = "Avatar",
                    modifier = Modifier.size(45.dp).clip(CircleShape)
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