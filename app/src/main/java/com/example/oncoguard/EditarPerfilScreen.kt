package com.example.oncoguard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

@Composable
fun EditarPerfilScreen(navController: NavController){
    Scaffold(
        bottomBar = { CustomBottomBar(navController = navController) },
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
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,

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
                    text = "Editar perfil",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Box( modifier = Modifier
                .clip(RoundedCornerShape(bottomEnd = 100.dp, bottomStart = 100.dp))
                .width(214.dp)
                .background(Color(0xFF54A1E0))
                .padding(start = 28.dp, end = 28.dp, top = 0.dp, bottom = 28.dp)
            ) {
                Box(modifier = Modifier
                    .height(155.dp)
                    .width(155.dp)
                    .clip(RoundedCornerShape(150.dp))
                    .background(Color(0xFFB60158))
                    .padding(15.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.iconnavbar),
                        contentDescription = "Avatar",
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.TopCenter,
                        modifier = Modifier
                            .clip(CircleShape)
                            .fillMaxSize()
                    )
                }
            }


            var nome by remember { mutableStateOf("") }
            var telefone by remember { mutableStateOf("") }
            var aniversario by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }

            Column(
                modifier = Modifier
                    .padding(horizontal = 40.dp)
                    .padding(top = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                OutlinedTextField(
                    value = nome,
                    onValueChange = { nome = it },
                    label = { Text("Nome", color = Color(0x5EB60158)) },
                    shape = RoundedCornerShape(10.dp)
                )

                OutlinedTextField(
                    value = aniversario,
                    onValueChange = { aniversario = it },
                    label = { Text("Aniversário", color = Color(0x5EB60158)) },
                    shape = RoundedCornerShape(10.dp)
                )

                OutlinedTextField(
                    value = telefone,
                    onValueChange = { telefone = it },
                    label = { Text("Telefone", color = Color(0x5EB60158)) },
                    shape = RoundedCornerShape(10.dp)
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("E-mail", color = Color(0x5EB60158)) },
                    shape = RoundedCornerShape(10.dp)
                )

                Button(
                    onClick = { navController.navigate(Screen.Home.route) },
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(0.87f)
                        .height(50.dp),
                    shape = RoundedCornerShape(60.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB60158))
                ) {
                    Text(
                        text = "Salvar",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditPreview() {
    EditarPerfilScreen(
        navController = NavController(LocalContext.current) // TODO()
    )
}