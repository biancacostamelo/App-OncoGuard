package com.example.oncoguard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun TelaEsperanca(navController: NavController){

    Scaffold(
        bottomBar = { CustomBottomBar(navController = navController) },
        topBar = {
            CustomTopAppBar(
                title = "Voltar",
                navigationIcon = Icons.Default.Info,
                showBackButton = true,
                navController = navController
            )
        }
    )
    { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            // Barra azul superior
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .background(Color(0xFF54A1E0)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Esperança",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }// Conteúdo principal
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF54A1E0)),
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    shape = RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp),
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        // 🧘 Imagem e título lado a lado
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.personagem_esperanca), // nome do arquivo da imagem
                                contentDescription = "Personagem meditando",
                                modifier = Modifier
                                    .size(80.dp)
                                    .padding(end = 8.dp),
                                contentScale = ContentScale.Fit
                            )

                            Text(
                                text = "O Significado Ativo da Esperança",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFB60158),
                                textAlign = TextAlign.Center
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = """
A esperança é a força que floresce mesmo em tempos difíceis.
Cada mulher que enfrenta o câncer de mama carrega dentro de si 
uma luz poderosa — a coragem de seguir, acreditar e recomeçar.
Você não está sozinha. A esperança vive em cada passo, em cada respiro e em cada novo dia.
                            """.trimIndent(),
                            fontSize = 16.sp,
                            color = Color.DarkGray,
                            textAlign = TextAlign.Justify,
                            lineHeight = 22.sp
                        )
                    }
                }
            }
        }
    }
}