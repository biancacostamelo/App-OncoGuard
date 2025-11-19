package com.example.oncoguard.feature.home

import androidx.navigation.NavController

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.oncoguard.R
import com.example.oncoguard.core.components.CustomBottomBar
import com.example.oncoguard.feature.home.PinkHeader

val PinkHeader = Color(0xFFFEE8FC)
val PinkCard = Color(0xFFC71585)
val BlueFooter = Color(0xFF64B5F6)
val TextPrimary = Color(0xFF333333)

data class HistoriasComunidade(
    val id: Int,
    val nome: String,
    val idade: Int,
    val historia: String,
    val avatarResId: Int
)

val historiasExemplo = mutableListOf(
    HistoriasComunidade(
        1,
        "ELISA",
        22,
        "... A vida, agora, tinha um sabor mais doce. Cada amanhecer era uma conquista. Ela havia perdido muito, mas ganhara a si mesma. E isso era tudo.",
        R.drawable.elisa_avatar
    ),
    HistoriasComunidade(
        2,
        "CELINE",
        35,
        "Ela não superou por ter apagado o passado. Ela superou por ter se reconstruído, mais forte, mais gentil, e infinitamente mais viva...",
        R.drawable.celine_avatar
    )
)

@Composable
fun TelaComunidade(navController: NavController) {
    Scaffold(
        bottomBar = { CustomBottomBar(navController = navController) },
        contentWindowInsets = WindowInsets.safeDrawing
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(PinkHeader)
                .padding(paddingValues)
        ) {
            HeaderComunidade(navController = navController)

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    InputHistoria()
                    Spacer(modifier = Modifier.height(16.dp))
                }

                items(historiasExemplo) { historia ->
                    HistoriaCard(historia = historia)
                }

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Veja mais >",
                            color = PinkCard,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            modifier = Modifier.clickable { }
                        )
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }
}

@Composable
fun HeaderComunidade(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(PinkHeader)
            .padding(top = 8.dp, bottom = 24.dp, start = 16.dp, end = 16.dp)
    ) {
        Text(
            text = "< Voltar",
            color = Color(0xFFB60158),
            fontSize = 18.sp,
            modifier = Modifier.clickable { navController.popBackStack() }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Comunidade",
            color = PinkCard,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 32.sp
        )
        Text(
            text = "Compartilhe sua ideia! Escreva aqui e inspire outros.",
            color = TextPrimary,
            fontSize = 14.sp
        )
    }
}

@Composable
fun InputHistoria() {
    var texto by remember { mutableStateOf("") }
    OutlinedTextField(
        value = texto,
        onValueChange = { texto = it },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .clip(RoundedCornerShape(25.dp)),
        placeholder = {
            Text(
                "Escreva sua história...",
                color = Color(0xFFBCBCBC)
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Send,
                contentDescription = "Enviar",
                tint = PinkCard,
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        historiasExemplo.add(
                            HistoriasComunidade(
                                id = historiasExemplo.size + 1,
                                nome = "Alicia",
                                idade = 65,
                                historia = texto, // aqui entra o valor digitado
                                avatarResId = R.drawable.iconnavbar
                            )
                        )
                        texto = ""
                    }
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = PinkCard,
            unfocusedBorderColor = PinkCard.copy(alpha = 0.5f),
            cursorColor = PinkCard,
            focusedTextColor = TextPrimary,
            unfocusedTextColor = Color.Gray
        ),
        shape = RoundedCornerShape(25.dp),
        textStyle = LocalTextStyle.current.copy(fontSize = 16.sp),
        singleLine = true
    )
}


@Composable
fun HistoriaCard(historia: HistoriasComunidade) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(PinkCard)
            .padding(20.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .background(Color.White)
                .padding(4.dp)
                .clip(CircleShape)
        ) {
            Image(
                painter = painterResource(id = historia.avatarResId),
                contentDescription = historia.nome,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = "${historia.nome}, ${historia.idade}",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = historia.historia,
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 16.sp,
                lineHeight = 22.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTelaComunidade() {
    val navController = rememberNavController()
    TelaComunidade(navController = navController)
}