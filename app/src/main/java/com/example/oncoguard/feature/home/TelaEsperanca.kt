package com.example.oncoguard.feature.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.oncoguard.core.components.CustomBottomBar
import com.example.oncoguard.core.components.CustomTopAppBar
import com.example.oncoguard.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaEsperanca(navController: NavController) {

    val esperancas = listOf(
        Esperanca(
            titulo = "A força da esperança",
            subtitulo = "A esperança é uma flor que insiste em nascer, mesmo nas terras mais secas.",
            conteudo = """
               Em cada dia de tratamento, em cada passo dado, há um lembrete: você é mais forte do
               que imagina. O câncer pode tentar abalar o corpo, mas nunca conseguirá apagar o 
               brilho da sua alma.
            """.trimIndent(),
            imagem = R.drawable.esp1
        ),
        Esperanca(
            titulo = "Um dia de cada vez",
            subtitulo = "Nem todos os dias serão fáceis, e tudo bem.",
            conteudo = """
                O importante é seguir um passo, um respiro, um sorriso de cada vez.
                A esperança não exige pressa, ela caminha ao seu lado, silenciosa, 
                mas firme, lembrando que a vida ainda floresce dentro de você.
            """.trimIndent(),
            imagem = R.drawable.esp2
        ),
        Esperanca(
            titulo = "Recomeços",
            subtitulo = "Há beleza em cada recomeço.",
            conteudo = """Mesmo quando o espelho mostra mudanças, você continua 
            sendo a mesma mulher incrível e inteira. A esperança é a luz que
            guia de volta à vida — mais leve, mais sábia e mais forte.
            """.trimIndent(),
            imagem = R.drawable.esp3
        ),
        Esperanca(
            titulo = "A vida floresce",
            subtitulo = "Mesmo em meio à dor, a vida encontra jeitos de florescer.",
            conteudo = """
                Que cada amanhecer traga um pouco mais de alívio, amor e confiança.
                A esperança é a semente que o câncer nunca poderá arrancar.
                Ela vive em você e continuará florescendo, linda e corajosa.
            """.trimIndent(),
            imagem = R.drawable.esp5
        ),
        Esperanca(
            titulo = "A coragem de acreditar",
            subtitulo = "A esperança é a coragem disfarçada de calma.",
            conteudo = """
                Ela sussurra quando o medo grita, e segura sua mão quando tudo 
                parece incerto. Acredite: dias melhores estão a caminho, e você 
                está construindo um deles agora, com sua força e fé.
            """.trimIndent(),
            imagem = R.drawable.esp4
        )
    )

    var esperancaAtual by remember { mutableStateOf(0) }
    val esperanca = esperancas[esperancaAtual]

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
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(paddingValues)
        ) {

            // Cabeçalho fixo, mas ainda rola junto
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
            }
            Box(modifier = Modifier.background(Color(0xFF54A1E0))) {
                // Conteúdo que deve rolar
                Surface(
                    shape = RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp),
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth().padding(top = 20.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .padding(33.dp)
                    ) {

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Crossfade(targetState = esperanca) { dicaAtual ->
                                Image(
                                    painter = painterResource(id = dicaAtual.imagem),
                                    contentDescription = "Personagem",
                                    modifier = Modifier
                                        .height(153.dp)
                                        .width(91.dp)
                                        .padding(end = 12.dp)
                                )
                            }

                            Column(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = esperanca.titulo,
                                    color = Color(0xFFB60158),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 26.sp,
                                )
                                Text(
                                    text = esperanca.subtitulo,
                                    fontSize = 14.sp,
                                    color = Color(0xFFB60158),
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "Renascendo a Cada Amanhecer:",
                            color = Color(0xFFB60158),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = esperanca.conteudo,
                            color = Color.DarkGray,
                            lineHeight = 24.sp,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Button(
                            onClick = {
                                esperancaAtual = (esperancaAtual + 1) % esperancas.size
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFB60158),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(30.dp),
                            modifier = Modifier
                                .padding(top = 24.dp, bottom = 16.dp)
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(
                                text = "Floresça",
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


data class Esperanca(
    val titulo: String,
    val subtitulo: String,
    val conteudo: String,
    val imagem: Int
)


@Preview(showBackground = true)
@Composable
fun EsperancaPreview() {
    TelaEsperanca(
        navController = NavController(LocalContext.current) // TODO()
    )
}