package com.example.oncoguard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ConfigScreen(navController: NavController) {
    Scaffold(
        bottomBar = { CustomBottomBar() },
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .background(Color(0xFF54A1E0)),
                contentAlignment = Alignment.Center
            )
            {
                Text(
                    text = "Configurações",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(21.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .height(192.dp)
                        .fillMaxWidth()
                        .background(Color(0xFFF4CAE7))
                        .padding(horizontal = 38.dp, vertical = 46.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.iconnavbar),
                            contentDescription = "Avatar",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(90.dp)
                                .clip(CircleShape)
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Row(
                                modifier = Modifier,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Editar perfil",
                                    color = Color(color = 0xFFB60158),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                                IconButton(onClick = { /* ação adicionar */ }) {
                                    Icon(
                                        Icons.Filled.Edit,
                                        "Localized description",
                                        tint = Color(0xFFB60158)
                                    )
                                }

                            }
                            Button(
                                onClick = { },
                                modifier = Modifier
                                    .fillMaxWidth(fraction = 0.8f)
                                    .height(40.dp),
                                shape = RoundedCornerShape(60.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(
                                        0xFFB60158
                                    )
                                )
                            ) {
                                Text(
                                    text = "Assinar planos",
                                    color = Color.White,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }

                Column() {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .drawBehind {
                                // Linha inferior (2.dp, rosa)
                                val strokeWidth = 1.dp.toPx()
                                val y = size.height - strokeWidth / 2
                                drawLine(
                                    color = Color(0xFFB60158),
                                    start = Offset(0f, y),
                                    end = Offset(size.width, y),
                                    strokeWidth = strokeWidth
                                )
                            }
                            .padding(vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { /* ação adicionar */ }) {
                            Icon(
                                Icons.Filled.Edit,
                                "Localized description",
                                tint = Color(0xFFB60158)
                            )
                        }
                        Text(
                            text = "Notificações",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(color = 0xFFB60158)
                        )
                    }

                    Row(
                        modifier = Modifier

                            .fillMaxWidth()
                            .drawBehind {
                                // Linha inferior (2.dp, rosa)
                                val strokeWidth = 1.dp.toPx()
                                val y = size.height - strokeWidth / 2
                                drawLine(
                                    color = Color(0xFFB60158),
                                    start = Offset(0f, y),
                                    end = Offset(size.width, y),
                                    strokeWidth = strokeWidth
                                )
                            }
                            .padding(vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { /* ação adicionar */ }) {
                            Icon(
                                Icons.Filled.Info,
                                "Localized description",
                                tint = Color(0xFFB60158)
                            )
                        }
                        Text(
                            text = "Documentos",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(color = 0xFFB60158)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()

                            .drawBehind {
                                // Linha inferior (2.dp, rosa)
                                val strokeWidth = 1.dp.toPx()
                                val y = size.height - strokeWidth / 2
                                drawLine(
                                    color = Color(0xFFB60158),
                                    start = Offset(0f, y),
                                    end = Offset(size.width, y),
                                    strokeWidth = strokeWidth
                                )
                            }
                            .padding(vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { /* ação adicionar */ }) {
                            Icon(
                                Icons.Filled.LocationOn,
                                "Localized description",
                                tint = Color(0xFFB60158)
                            )
                        }
                        Text(
                            text = "Idioma",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(color = 0xFFB60158)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()

                            .drawBehind {
                                // Linha inferior (2.dp, rosa)
                                val strokeWidth = 1.dp.toPx()
                                val y = size.height - strokeWidth / 2
                                drawLine(
                                    color = Color(0xFFB60158),
                                    start = Offset(0f, y),
                                    end = Offset(size.width, y),
                                    strokeWidth = strokeWidth
                                )
                            }
                            .padding(vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { /* ação adicionar */ }) {
                            Icon(
                                Icons.Filled.Share,
                                "Localized description",
                                tint = Color(0xFFB60158)
                            )
                        }
                        Text(
                            text = "Compartilhar",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(color = 0xFFB60158)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .drawBehind {
                                // Linha inferior (2.dp, rosa)
                                val strokeWidth = 1.dp.toPx()
                                val y = size.height - strokeWidth / 2
                                drawLine(
                                    color = Color(0xFFB60158),
                                    start = Offset(0f, y),
                                    end = Offset(size.width, y),
                                    strokeWidth = strokeWidth
                                )
                            }
                            .padding(vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { /* ação adicionar */ }) {
                            Icon(
                                Icons.Filled.ExitToApp,
                                "Localized description",
                                tint = Color(0xFFB60158)
                            )
                        }
                        Text(
                            text = "Sair",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(color = 0xFFB60158)
                        )
                    }

                }


            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConfigPreview() {
    ConfigScreen(
        navController = NavController(LocalContext.current) // TODO()
    )
}