package com.example.oncoguard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
fun TelaBemEstar(navController: NavController) {
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
    ) { paddingValues ->

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
                    text = "Bem Estar",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Conteúdo principal
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
                                painter = painterResource(id = R.drawable.personagem_bemestar), // nome do arquivo da imagem
                                contentDescription = "Personagem meditando",
                                modifier = Modifier
                                    .size(80.dp)
                                    .padding(end = 8.dp),
                                contentScale = ContentScale.Fit
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = """
O bem-estar é uma parte essencial do processo de recuperação e qualidade de vida. 
Aqui você encontrará dicas, orientações e práticas para cuidar da mente e do corpo.

• Mantenha uma alimentação equilibrada.  
• Reserve momentos do dia para relaxar.  
• Pratique exercícios leves, conforme orientação médica.  
• Cultive pensamentos positivos e boas relações.  

Lembre-se: cuidar de si mesmo é parte fundamental do tratamento.
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