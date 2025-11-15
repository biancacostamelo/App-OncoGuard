package com.example.oncoguard.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.handwriting.handwritingDetector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.oncoguard.R
import com.example.oncoguard.core.components.CustomBottomBar
import com.example.oncoguard.core.navigation.Screen

data class ButtonData(
    val title: String,
    val imageRes: Int,
    val description: String
)

@Composable
fun HomeScreen(navController: NavController) {
    var showSearchBar by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    var searchResults by remember { mutableStateOf<List<ButtonData>>(emptyList()) }
    var isSearching by remember { mutableStateOf(false) }

    // Lista de opções com título, imagem e descrição
    val allOptions = listOf(
        ButtonData("Dicas", R.drawable.personagem_dica, "Dicas para o dia a dia."),
        ButtonData("Acolhimento", R.drawable.personagem_acolhimento, "Espaço para apoio e empatia."),
        ButtonData("Esperança", R.drawable.personagem_esperanca, "Mensagens inspiradoras."),
        ButtonData("Bem Estar", R.drawable.personagem_bemestar, "Cuide da mente e do corpo."),
        ButtonData("Médico", R.drawable.medico, "Acompanhe hospitais e clinicas perto de você."),
        ButtonData("Calendário", R.drawable.calendario, "Organize seus compromissos de saúde."),
        ButtonData("ONGs", R.drawable.ong, "Encontre apoio e redes solidárias.")
    )

    // Função de pesquisa
    fun performSearch(query: String) {
        isSearching = true
        searchResults = if (query.isEmpty()) {
            emptyList()
        } else {
            allOptions.filter { it.title.contains(query, ignoreCase = true) }
        }
    }

    Scaffold(
        bottomBar = { CustomBottomBar(navController) },
        contentWindowInsets = WindowInsets.systemBars, // protege topo + nav bar
        containerColor = Color.Transparent,
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        // importante: aplicar innerPadding para não sobrepor status bar
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // <- ESSENCIAL para proteger topo e fundo
                .background(Color(0xFFFEFDF9)) // seu rosa de fundo (exemplo)
        ) {
            Box(
                modifier = Modifier

                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { navController.navigate(Screen.ConfigScreen.route) }) {
                        Icon(
                            Icons.Filled.AccountCircle,
                            contentDescription = "Perfil",
                            modifier = Modifier.size(50.dp),
                            tint =  Color(0xffB60158),
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Olá, Alice!",
                        color = Color(0xffB60158),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    IconButton(
                        onClick = {
                            showSearchBar = !showSearchBar
                            if (!showSearchBar) {
                                searchText = ""
                                searchResults = emptyList()
                                isSearching = false
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (showSearchBar) Icons.Filled.Close else Icons.Filled.Search,
                            contentDescription = "Pesquisar",
                            modifier = Modifier.size(34.dp),
                            tint =  Color(0xffB60158)
                        )
                    }
                }
            }

            if (showSearchBar) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF0D6EFD))
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = searchText,
                        onValueChange = {
                            searchText = it
                            performSearch(it)
                        },
                        placeholder = { Text("Pesquisar...") },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp),
                        singleLine = true
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(modifier = Modifier.weight(1f),  color = Color(0xffB60158), thickness = 1.dp)

                Text(
                    text = "Bem-Vinda(o)!",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = Color(0xffB60158),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Medium
                )

                Divider(modifier = Modifier.weight(1f),  color = Color(0xffB60158), thickness = 1.dp)
            }

            val itemsToDisplay = if (showSearchBar && isSearching) searchResults else allOptions

            if (itemsToDisplay.isEmpty() && showSearchBar) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (searchText.isEmpty()) "Digite para pesquisar" else "Nenhum resultado encontrado",
                        color = Color.Gray,
                        fontSize = 16.sp
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(itemsToDisplay) { item ->
                        ButtonItemWithImage(
                            text = item.title,
                            imageRes = item.imageRes,
                            description = item.description
                        ) {
                            when (item.title) {
                                "Dicas" -> navController.navigate(Screen.TelaDicas.route)
                                "Acolhimento" -> navController.navigate(Screen.TelaAcolhimento.route)
                                "Esperança" -> navController.navigate(Screen.TelaEsperanca.route)
                                "Bem Estar" -> navController.navigate(Screen.TelaBemEstar.route)
                                "Médico" -> navController.navigate(Screen.TelaMedico.route)
                                "Calendário" -> navController.navigate(Screen.TelaCalendario.route)
                                "ONGs" -> navController.navigate(Screen.TelaONGS.route)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ButtonItemWithImage(
    text: String,
    imageRes: Int,
    description: String,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(265.dp)
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(20.dp),
                clip = false, // deixa a sombra aparecer para fora
                ambientColor = Color(0xFF42031E), // sombra suave
                spotColor = Color(0xFF42031E),

            )
            .background(Color.White, RoundedCornerShape(20.dp))
            .clickable { onClick() } // se quiser clique
            .padding(bottom = 8.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = text,
            modifier = Modifier
                .fillMaxWidth()
                .height(176.dp)
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxHeight()
        ) {
            Text(
                text = text,
                color = Color(0xffB60158),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = description,
                color = Color(0xff595959),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = NavController(LocalContext.current))
}