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
fun ONGtres(navController: NavController) {

    val images = listOf(
        R.drawable.mamasdoamor,
        R.drawable.mamasdoamor2,
        R.drawable.mamasdoamor3,
        R.drawable.mamasdoamor4,
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
                            contentDescription = "Mamas do Amor",
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
                            text = "Mamas do Amor",
                            color = Color(0xFFB0004D),
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "O Mamas do Amor produz próteses externas de alpiste para mulheres mastectomizadas, oferecendo acolhimento, conforto, autoestima e suporte emocional para quem está passando pela jornada do câncer de mama. A ONG atua com carinho, cuidado e dedicação para ajudar mulheres a se sentirem confiantes e acolhidas.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF4A4A4A)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        ContactItem(
                            icon = Icons.Default.Phone,
                            text = "+55 11 97558-2092",
                            url = "tel:+5511975582092"
                        )

                        ContactItem(
                            icon = Lucide.Instagram,
                            text = "@mamasdoamor",
                            url = "https://instagram.com/mamasdoamor"
                        )

                        ContactItem(
                            icon = Lucide.Link,
                            text = "facebook.com/mamasdoamor",
                            url = "https://facebook.com/mamasdoamor"
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ONGtresPreview() {
    ONGtres(
        navController = NavController(LocalContext.current)
    )
}
