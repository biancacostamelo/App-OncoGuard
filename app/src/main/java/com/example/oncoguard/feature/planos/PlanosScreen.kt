package com.example.oncoguard.feature.planos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.oncoguard.core.components.CustomBottomBar2
import com.example.oncoguard.R
import com.example.oncoguard.core.navigation.Screen

@Composable
fun PlanosScreen(navController: NavController) {
    Scaffold(
        bottomBar = { CustomBottomBar2(navController = navController) },
        contentWindowInsets = WindowInsets.safeDrawing
    ) { paddingValues ->
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(Color(0xFF54A1E0))
                .padding(paddingValues)
                .padding(horizontal = 22.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 40.dp)
            ) {

                Text("PLANOS", color = Color.White, fontSize = 36.sp, fontWeight = FontWeight.Bold)
                Text(
                    "Navegue sem limites!",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier.fillMaxSize().padding(bottom = 20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)
                        .fillMaxWidth()
                        .height(174.dp)
                        .padding(22.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Column {
                        Text(
                            "Diamante",
                            color = Color(0xFFB60158),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                "R\$ 230,00",
                                color = Color(0x78B60158),
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(7.dp))
                            Text(
                                "R$/ mês",
                                color = Color(0x9F232323),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(top = 10.dp)
                            )
                        }
                        Button(
                            onClick = { navController.navigate(Screen.PlanoDiamante.route) },
                            modifier = Modifier
                                .width(184.dp),
                            shape = RoundedCornerShape(60.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB60158)),
                        ) {
                            Text(
                                text = "Obter Diamante",
                                color = Color.White,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                    Column(modifier = Modifier.padding(start = 30.dp)) {
                        Image(
                            painter = painterResource(R.drawable.planosdiamante),
                            contentDescription = "plano diamante",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)
                        .fillMaxWidth()
                        .height(174.dp)
                        .padding(22.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Column {
                        Text(
                            "Ouro",
                            color = Color(0xFFB60158),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                "R\$ 200,00",
                                color = Color(0x78B60158),
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(7.dp))
                            Text(
                                "R$/ mês",
                                color = Color(0x9F232323),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(top = 10.dp)
                            )
                        }
                        Button(
                            onClick = { navController.navigate(Screen.PlanoOuro.route) },
                            modifier = Modifier
                                .width(184.dp),
                            shape = RoundedCornerShape(60.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB60158))
                        ) {
                            Text(
                                text = "Obter Ouro",
                                color = Color.White,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                    Column(modifier = Modifier.padding(start = 30.dp)) {
                        Image(
                            painter = painterResource(R.drawable.planosouro),
                            contentDescription = "plano diamante",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)
                        .fillMaxWidth()
                        .height(174.dp)
                        .padding(22.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Column {
                        Text(
                            "Prata",
                            color = Color(0xFFB60158),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                "R\$ 0,00",
                                color = Color(0x78B60158),
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(7.dp))
                            Text(
                                "R$/ mês",
                                color = Color(0x9F232323),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(top = 10.dp)
                            )
                        }
                        Button(
                            onClick = { navController.navigate(Screen.PlanoPrata.route) },
                            modifier = Modifier
                                .width(184.dp),
                            shape = RoundedCornerShape(60.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB60158))
                        ) {
                            Text(
                                text = "Plano atual",
                                color = Color.White,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                    Column(modifier = Modifier.padding(start = 30.dp)) {
                        Image(
                            painter = painterResource(R.drawable.planosprata),
                            contentDescription = "plano diamante",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlanosPreview() {
    PlanosScreen(
        navController = NavController(LocalContext.current) // TODO()
    )
}