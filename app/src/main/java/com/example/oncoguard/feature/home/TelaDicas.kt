package com.example.oncoguard.feature.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun TelaDicas(navController: NavController) {

    val dicas = listOf(
        Dica(
            titulo = "Dieta equilibrada",
            subtitulo = "Sabia que após o câncer de mama,\nprecisa fazer uma dietinha?",
            conteudo = "Aumente o consumo desses alimentos Frutas, Legumes e Verduras: Pelo menos 2​ xícaras por dia, priorizando a variedade e cores diferentes. São fontes de vitaminas, minerais e antioxidantes. \nCereais Integrais:  Como arroz integral, pães e massas integrais, aveia e quinoa. Leguminosas, Sementes e Nozes: Feijões, lentilha, grão de bico, castanhas e sementes (linhaça, chia).",
            imagem = R.drawable.personagem_dica2
        ),
        Dica(
            titulo = "Atividade física",
            subtitulo = "Mexa-se! Exercícios ajudam na recuperação e bem-estar.",
            conteudo = """
                A prática regular de exercícios traz muitos benefícios:

                • Caminhe ou pratique exercícios leves pelo menos 30 minutos por dia.
                • Evite o sedentarismo e alongue-se com frequência.
                • Consulte seu médico sobre atividades seguras para o seu caso.
            """.trimIndent(),
            imagem = R.drawable.personagem_exercicio
        ),
        Dica(
            titulo = "Sono de qualidade",
            subtitulo = "Dormir bem é parte essencial da recuperação.",
            conteudo = """
                • Tente dormir entre 7 a 9 horas por noite.
                • Evite telas e café antes de dormir.
                • Crie uma rotina calma antes de se deitar.
            """.trimIndent(),
            imagem = R.drawable.personagem_sono
        ),
        Dica(
            titulo = "Controle emocional",
            subtitulo = "A mente também precisa de cuidados!",
            conteudo = """
                • Busque apoio psicológico, se sentir necessidade.
                • Converse com familiares e amigos sobre seus sentimentos.
                • Práticas como meditação e respiração ajudam a relaxar.
            """.trimIndent(),
            imagem = R.drawable.personagem_mental
        ),
        Dica(
            titulo = "Hidratação",
            subtitulo = "A água é sua melhor aliada todos os dias!",
            conteudo = """
                • Beba pelo menos 2 litros de água por dia.
                • Evite refrigerantes e bebidas alcoólicas.
                • Carregue sempre uma garrafinha com você.
            """.trimIndent(),
            imagem = R.drawable.personagem_agua
        )
    )

    var dicaAtual by remember { mutableStateOf(0) }
    val dica = dicas[dicaAtual]

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
            // 🔹 Barra superior colorida
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .background(Color(0xFF54A1E0)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Dicas",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // 🔹 Conteúdo principal
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
                            .padding(33.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))

                        // 🔹 Cabeçalho com imagem + título
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Crossfade(targetState = dica) { dicaAtual ->
                                Image(
                                    painter = painterResource(id = dicaAtual.imagem),
                                    contentDescription = "Personagem",
                                    modifier = Modifier
                                        .height(153.dp)
                                        .width(91.dp)
                                        .padding(end = 12.dp)
                                )
                            }

                            Column(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = dica.titulo,
                                    color = Color(0xFFB60158),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 26.sp,
                                )
                                Text(
                                    text = dica.subtitulo,
                                    fontSize = 14.sp,
                                    color = Color(0xFFB60158),
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // 🔹 Texto principal fixo (usa weight para não mexer)
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Aqui vai uma dica pra você:",
                                color = Color(0xFFB60158),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = dica.conteudo,
                                fontSize = 15.sp,
                                color = Color.Black,
                                modifier = Modifier.fillMaxWidth(),
                                lineHeight = 30.sp
                            )
                        }

                        // 🔹 Botão fixo no final
                        Button(
                            onClick = {
                                dicaAtual = (dicaAtual + 1) % dicas.size
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFB60158),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(30.dp),
                            modifier = Modifier
                                .padding(top = 24.dp, bottom = 16.dp)
                                .fillMaxWidth(0.7f)
                                .height(50.dp)
                        ) {
                            Text(
                                text = "Ver mais dicas",
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

// 🔹 Modelo de dados
data class Dica(
    val titulo: String,
    val subtitulo: String,
    val conteudo: String,
    val imagem: Int
)

@Preview(showBackground = true)
@Composable
fun DicasPreview() {
    TelaDicas(navController = NavController(LocalContext.current))
}