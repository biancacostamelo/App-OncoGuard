package com.example.oncoguard.feature.planos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.composables.icons.lucide.Folder
import com.composables.icons.lucide.Globe
import com.composables.icons.lucide.Group
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.MessageSquare
import com.composables.icons.lucide.Mic
import com.composables.icons.lucide.Trash
import com.example.oncoguard.R
import com.example.oncoguard.core.components.CustomBottomBar2
import com.example.oncoguard.core.components.CustomTopAppBar
import com.example.oncoguard.core.navigation.Screen




@Composable
fun PlanoDiamante(navController: NavController) {
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
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFF54A1E0))
                .padding(32.dp)
                .clip(shape = RoundedCornerShape(20.dp))
        ) {
            Column (modifier = Modifier
                .background(Color(0xFFFFFFFF))
                .fillMaxSize()
                .padding(horizontal = 40.dp, vertical = 28.dp)
            ) {
                Row( verticalAlignment = Alignment.CenterVertically ) {
                    Text(text = "DIAMANTE", color = Color(0xFFB60158), fontSize = 36.sp, fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.width(22.dp))
                    Image(
                        painter = painterResource(R.drawable.planosdiamante),
                        contentDescription = "plano diamante",
                        modifier = Modifier
                            .height(96.dp)
                            .width(55.dp)
                    )
                }
                Column(){
                    Text(text = "R\$ 230,00", color = Color(0xFFB60158), fontSize = 48.sp, fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Acesso completo", color = Color(0xFFB60158), fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
                }
                Column( verticalArrangement = Arrangement.SpaceAround, modifier = Modifier
                    .fillMaxSize()) {
                    Row (verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Lucide.MessageSquare,
                            contentDescription = "Message Square",
                            tint = Color(0xFFB60158),

                        )
                        Spacer(modifier = Modifier.width(15.dp))
                        Text(text = "Mensagens ILIMITADAS e respostas mais rapidas", color = Color(0xFFB60158))
                    }

                    Row (verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Lucide.Folder,
                            contentDescription = "Folder Icon",
                            tint = Color(0xFFB60158),

                        )
                        Spacer(modifier = Modifier.width(15.dp))
                        Text(text = "Mais arquivos e memória ILIMITADA", color = Color(0xFFB60158))
                    }

                    Row (verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Lucide.Globe,
                            contentDescription = "Message Square",
                            tint = Color(0xFFB60158),

                        )
                        Spacer(modifier = Modifier.width(15.dp))
                        Text(text = "Localização de voluntarios no BRASIL", color = Color(0xFFB60158))
                    }

                    Row (verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Lucide.Mic,
                            contentDescription = "Message Square",
                            tint = Color(0xFFB60158),

                        )
                        Spacer(modifier = Modifier.width(15.dp))
                        Text(text = "Chat por voz ILIMITADO", color = Color(0xFFB60158))
                    }

                    Row (verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Lucide.Group,
                            contentDescription = "Message Square",
                            tint = Color(0xFFB60158),

                        )
                        Spacer(modifier = Modifier.width(15.dp))
                        Text(text = "Acesso TOTAL a comunidade", color = Color(0xFFB60158))
                    }

                    Row (verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Lucide.Trash,
                            contentDescription = "Message Square",
                            tint = Color(0xFFB60158),

                        )
                        Spacer(modifier = Modifier.width(15.dp))
                        Text(text = "Recuperação de mensagens apagadas ", color = Color(0xFFB60158))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlanosDiamantePreview() {
    PlanoDiamante(
        navController = NavController(LocalContext.current) // TODO()
    )
}