package com.example.oncoguard.feature.ongs

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.oncoguard.core.components.CustomTopAppBar
import com.example.oncoguard.R

@Composable
fun ONGquatro(navController: NavController) {
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

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFF54A1E0))
                .padding(32.dp)
                .clip(shape = RoundedCornerShape(20.dp))
        ) {

            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize()
                    .padding(horizontal = 30.dp, vertical = 28.dp)
                    .verticalScroll(scrollState)
            ) {

                // Título
                Text(
                    text = "Associação das Mulheres de Peito",
                    color = Color(0xFFB60158),
                    fontSize = 36.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 36.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Atendimento \npsicológico: ",
                        color = Color(0xFFB60158),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "telefone (37) 9 9843-1082",
                        color = Color(0xFFB60158),
                        //fontSize = 20.sp
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "E-mail: ",
                        color = Color(0xFFB60158),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "contato@mulheresdepeito.org.br",
                        color = Color(0xFFB60158),
                       // fontSize = 20.sp
                    )
                }
            }}
    }
}

@Preview(showBackground = true)
@Composable
fun ONGquatroPreview() {
    ONGquatro(
        navController = NavController(LocalContext.current)
    )
}