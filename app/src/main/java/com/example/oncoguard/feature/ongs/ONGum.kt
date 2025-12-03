package com.example.oncoguard.feature.ongs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.example.oncoguard.core.components.CustomTopAppBar
import com.example.oncoguard.R

@Composable
fun ONGum(navController: NavController) {

    val images = listOf(
        R.drawable.femama,
        R.drawable.femama2,
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

                    // ------------------------------
                    // CARROSSEL
                    // ------------------------------
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
                            contentDescription = "Imagem da ONG",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxHeight()
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

                    // ------------------------------
                    // CONTEÚDO DO CARD
                    // ------------------------------
                    Column(modifier = Modifier.padding(20.dp)) {

                        Text(
                            text = "Femama",
                            color = Color(0xFFB0004D),
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "A Femama - Federação Brasileira de Instituições Filantrópicas de Apoio à Saúde da Mama é uma associação civil, sem fins econômicos, que busca reduzir os índices de mortalidade por câncer de mama no Brasil.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF4A4A4A)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        ContactItemClickable(
                            icon = Icons.Default.Phone,
                            text = "+55 (51) 3094-0017",
                            url = "tel:+55513094-0017"
                        )
                        ContactItemClickable(
                            icon = Icons.Default.Email,
                            text = "contato@femama.org.br",
                            url = "mailto:contato@femama.org.br"
                        )
                        ContactItemClickable(
                            icon = Lucide.Link,
                            text = "https://femama.org.br/site/",
                            url = "https://femama.org.br/site/"
                        )
                        ContactItemClickable(
                            icon = Lucide.Instagram,
                            text = "@femama.brasil",
                            url = "https://instagram.com/femama.brasil"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ContactItemClickable(icon: ImageVector, text: String, url: String) {
    val uriHandler = LocalUriHandler.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 8.dp)
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
fun ONGumPreview() {
    ONGum(
        navController = NavController(LocalContext.current)
    )
}