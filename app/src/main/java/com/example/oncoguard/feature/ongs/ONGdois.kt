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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Phone
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.composables.icons.lucide.Instagram
import com.composables.icons.lucide.Link
import com.composables.icons.lucide.Lucide
import com.example.oncoguard.R
import com.example.oncoguard.core.components.CustomTopAppBar

@Composable
fun ONGdois(navController: NavController) {

    val images = listOf(
        R.drawable.cerasus,
        R.drawable.cerasus2
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

                    // ---------------------------------
                    // CARROSSEL
                    // ---------------------------------
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
                            contentDescription = "Fotos Instituto Cérasus",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Indicadores
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        repeat(images.size) { index ->
                            val isSelected = pagerState.currentPage == index
                            Box(
                                modifier = Modifier
                                    .padding(4.dp)
                                    .size(if (isSelected) 10.dp else 8.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (isSelected) Color(0xFFD81B60) else Color.LightGray
                                    )
                            )
                        }
                    }

                    // ---------------------------------
                    // CONTEÚDO DO CARD
                    // ---------------------------------

                    Column(modifier = Modifier.padding(20.dp)) {

                        Text(
                            text = "Instituto Cérasus",
                            color = Color(0xFFB0004D),
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "O Instituto Cérasus oferece acolhimento emocional, terapias integrativas, atividades de fortalecimento e apoio a mulheres em tratamento ou pós-tratamento do câncer de mama. O objetivo é promover bem-estar, autoestima, saúde emocional e uma rede de apoio segura e humanizada.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF4A4A4A)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Contatos
                        ContactItem(
                            icon = Icons.Default.Phone,
                            text = "+55 11 94534-4626",
                            url = "tel:+5511945344626"
                        )

                        ContactItem(
                            icon = Icons.Default.Email,
                            text = "contato@institutocerasus.org.br",
                            url = "mailto:contato@institutocerasus.org.br"
                        )

                        ContactItem(
                            icon = Lucide.Link,
                            text = "institutocerasus.org.br",
                            url = "https://institutocerasus.org.br"
                        )

                        ContactItem(
                            icon = Lucide.Instagram,
                            text = "@institutocerasus",
                            url = "https://instagram.com/institutocerasus"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ContactItem(icon: ImageVector, text: String, url: String) {
    val uriHandler = LocalUriHandler.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 6.dp)
            .clickable { uriHandler.openUri(url) }
    ) {
        Icon(
            icon,
            contentDescription = null,
            tint = Color(0xFFB0004D),
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(text, style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun ONGdoisPreview() {
    ONGdois(
        navController = NavController(LocalContext.current)
    )
}
