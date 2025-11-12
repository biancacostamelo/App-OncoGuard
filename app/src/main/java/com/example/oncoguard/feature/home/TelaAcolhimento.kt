package com.example.oncoguard.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
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
import com.example.oncoguard.core.components.CustomBottomBar
import com.example.oncoguard.core.components.CustomTopAppBar
import com.example.oncoguard.R

@Composable
fun TelaAcolhimento(navController: NavController) {

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
                    text = "Acolhimento",
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
                                painter = painterResource(id = R.drawable.personagem_acolhimento),
                                contentDescription = "Personagem acolhimento",
                                modifier = Modifier
                                    .size(80.dp)
                                    .padding(end = 8.dp),
                                contentScale = ContentScale.Fit
                            )

                            Text(
                                text = "Emocional e social!",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFB60158),
                                textAlign = TextAlign.Center
                            )
                        }

                        Text(
                            text = "Honre seus sentimentos e seja paciente consigo mesmo",
                            fontSize = 14.sp,
                            color = Color(0xFFB60158),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 8.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Mas como?",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFB60158),
                            textAlign = TextAlign.Justify
                        )

                        Text(
                            text = """
                           Pensando na sua luta diária, criamos uma comunidade para criar novas amizades.
                            """.trimIndent(),
                            fontSize = 16.sp,
                            color = Color.DarkGray,
                            textAlign = TextAlign.Justify,
                            lineHeight = 22.sp,
                            modifier = Modifier.padding(top = 8.dp)
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        // ✅ Botão dentro da tela
                        // TELA DICA até fizer a tela de comunidade
                        Button(
                            onClick = { navController.navigate("TelaDicas") },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFB60158),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(30.dp),
                            modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .height(50.dp)
                        ) {
                            Text(
                                text = "Comunidade",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}