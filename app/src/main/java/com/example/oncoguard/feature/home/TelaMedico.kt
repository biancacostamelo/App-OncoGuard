package com.example.oncoguard.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.oncoguard.R
import com.example.oncoguard.core.components.CustomBottomBar
import com.example.oncoguard.core.components.CustomTopAppBar


@Composable
fun TelaMedico(navController: NavController) {
    // Scaffold fornece a estrutura básica (barra superior, conteúdo, barra inferior)
    Scaffold(
        bottomBar = { CustomBottomBar(navController = navController) }, // Sua barra inferior
        topBar = {
            CustomTopAppBar(
                title = "Voltar",
                navigationIcon = Icons.Default.Info,
                showBackButton = true,
                navController = navController
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing
    ) { paddingValues ->
        // Column organiza os elementos verticalmente
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)

                // Permite rolar o conteúdo caso a tela seja pequena
                .verticalScroll(rememberScrollState())
        ) {
            // Chamamos as três grandes seções da tela:
            HeaderMedicos() // O topo azul com pesquisa e mapa
            TituloProximosAVoce() // O título "Próximos a você"
            ListaHospitais() // Os itens de hospitais
        }
    }
}

@Composable
fun HeaderMedicos() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF54A1E0)) // Fundo azul
            .padding(bottom = 20.dp) // Espaço abaixo do mapa
    ) {
        // A. Topo: Voltar e Título "Médicos & Hospitais"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 33.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Médicos & Hospitais",
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // B. Barra de Pesquisa
        OutlinedTextField(
            value = "",
            onValueChange = { /* Atualiza o estado da pesquisa */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 33.dp, vertical = 8.dp),
            placeholder = {
                Text(
                    "Pesquise um hospital próximo",
                    color = Color.White.copy(alpha = 0.7f)
                )
            },
            leadingIcon = {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = "Pesquisar",
                    tint = Color.White
                )
            },
            shape = RoundedCornerShape(25.dp), // **Chave para o arredondamento**
            colors = OutlinedTextFieldDefaults.colors( // Estilização para o fundo azul
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White.copy(alpha = 0.5f),
                unfocusedContainerColor = Color(0xFF54A1E0),
                focusedContainerColor = Color(0xFF54A1E0),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Sua localização:",
            color = Color.White.copy(alpha = 0.8f),
            modifier = Modifier.padding(start = 33.dp, bottom = 8.dp),
            fontSize = 14.sp
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 33.dp, vertical = 8.dp)
                .height(171.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()        // A Box ocupa 100% do Card
                    .background(Color(0xFFE8F5E9)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mapa),
                    contentDescription = "Localização no mapa",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}


@Composable
fun TituloProximosAVoce() {
    Text(
        text = "Próximos a você",
        color = HighlightPink, // Cor de destaque
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 33.dp, vertical = 12.dp).padding(top = 20.dp)
    )
}


@Composable
fun HospitalItem(nome: String, endereco: String, cidadeEstado: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 33.dp, vertical = 12.dp)
    ) {
        // Nome
        Text(
            text = nome,
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(4.dp))

        // Endereço
        Text(text = endereco, color = Color.Gray, fontSize = 14.sp)

        // Cidade e CEP
        Text(text = cidadeEstado, color = Color.Gray, fontSize = 14.sp)
    }
    // Adiciona um divisor para separar visualmente os itens
    HorizontalDivider(
        modifier = Modifier.padding(horizontal = 16.dp),
        color = Color.LightGray.copy(alpha = 0.5f)
    )
}

@Composable
fun ListaHospitais() {
    // Usamos Column para empilhar os itens de hospital
    Column {
        HospitalItem(
            nome = "UPA 24h",
            endereco = "Av. Central, 1234 – Bairro Jardim das Flores",
            cidadeEstado = "São Paulo – SP, 01234-567"
        )
        HospitalItem(
            nome = "Clínica Santa Aurora",
            endereco = "Rua das Palmeiras, 567 – Bairro Jardim das Flores",
            cidadeEstado = "São Paulo – SP, 01234-567"
        )
        // Adicione mais itens conforme necessário...
    }
}

val PrimaryBlue = Color(0xFF4C8CD2)
val HighlightPink = Color(0xFFC2185B) // Rosa/Magenta para o título


@Preview(showBackground = true)
@Composable
fun TelaMedicoPreview() {
    TelaMedico(navController = NavController(LocalContext.current))
}
