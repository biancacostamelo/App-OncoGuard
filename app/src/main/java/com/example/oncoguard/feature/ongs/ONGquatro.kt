package com.example.oncoguard.feature.ongs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.composables.icons.lucide.Instagram
import com.composables.icons.lucide.Link
import com.composables.icons.lucide.Lucide
import com.example.oncoguard.R
import com.example.oncoguard.core.components.CustomTopAppBar

@Composable
fun ONGquatro(navController: NavController) {

    val images = listOf(
        R.drawable.mulheresdepeito,
        R.drawable.mulheresdepeito2,
        R.drawable.mulheresdepeito3,
        R.drawable.mulheresdepeito4
    )

    Scaffold(
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

        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color(0xFF54A1E0))
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Card(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize(),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(6.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF8F6F6)
                )
            ) {
                Column {

                    //-----------------------------------
                    // CARROSSEL
                    //-----------------------------------
                    val pagerState = rememberPagerState(pageCount = { images.size })

                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier
                            .background(Color.White)
                            .fillMaxWidth()
                            .height(250.dp)
                    ) { page ->
                        Image(
                            painter = painterResource(id = images[page]),
                            contentDescription = "Associação das Mulheres de Peito",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Indicadores (bolinhas)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        repeat(images.size) { index ->
                            Box(
                                modifier = Modifier
                                    .padding(4.dp)
                                    .size(if (pagerState.currentPage == index) 10.dp else 8.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (pagerState.currentPage == index)
                                            Color(0xFFD81B60)
                                        else Color.LightGray
                                    )
                            )
                        }
                    }

                    //-----------------------------------
                    // CONTEÚDO DO CARD
                    //-----------------------------------
                    Column(modifier = Modifier.padding(20.dp)) {

                        Text(
                            text = "Associação das Mulheres de Peito",
                            color = Color(0xFFB0004D),
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "A Associação das Mulheres de Peito oferece apoio emocional, doação de próteses externas, rodas de conversa, orientação e acolhimento a mulheres que enfrentam o câncer de mama. O trabalho é baseado em empatia, autoestima e fortalecimento feminino durante toda a jornada do tratamento.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF4A4A4A)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        ContactItem(
                            icon = Icons.Default.Phone,
                            text = "+55 11 99720-1991",
                            url = "tel:+5511997201991"
                        )

                        ContactItem(
                            icon = Lucide.Instagram,
                            text = "@mulheresdepeitosp",
                            url = "https://instagram.com/mulheresdepeitosp"
                        )

                        ContactItem(
                            icon = Lucide.Link,
                            text = "mulheresdepeito.org.br",
                            url = "https://mulheresdepeito.org.br"
                        )

                        ContactItem(
                            icon = Lucide.Link,
                            text = "facebook.com/mulheresdepeitosp",
                            url = "https://facebook.com/mulheresdepeitosp"
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ONGquatroPreview() {
    ONGquatro(
        navController = NavController(LocalContext.current)
    )
}
