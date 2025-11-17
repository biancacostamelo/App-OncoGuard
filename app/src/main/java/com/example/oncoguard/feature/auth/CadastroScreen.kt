package com.example.oncoguard.feature.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.oncoguard.core.components.CustomTopAppBar
import com.example.oncoguard.core.navigation.Screen

@Composable
fun CadastroScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var nome by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Voltar",
                navigationIcon = Icons.Default.Info,
                showBackButton = true,
                navController = navController,
                titleColor = Color(0xFFFFFFFF)
            )
        }
    ) { paddingValues ->
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .background(Color(0xFF54A1E0)),
            verticalArrangement = Arrangement.Bottom
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(
                        Color(0xFFF3F3F3),
                        shape = RoundedCornerShape(topStart = 80.dp, topEnd = 80.dp)
                    )
                    .padding(horizontal = 40.dp, vertical = 80.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Cadastrar",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFB60158)
                )

                Column(
                    modifier = Modifier
                        .widthIn(max = 400.dp)
                        .fillMaxWidth(0.9f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    OutlinedTextField(
                        value = nome,
                        onValueChange = { nome = it },
                        label = { Text("Nome", color = Color(0xFF4F4E4E)) },
                        textStyle = TextStyle(color = Color(0xFF494949)),
                        shape = RoundedCornerShape(10.dp)
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email", color = Color(0xFF4F4E4E)) },
                        textStyle = TextStyle(color = Color(0xFF494949)),
                        shape = RoundedCornerShape(10.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        )
                    )


                    OutlinedTextField(
                        value = senha,
                        onValueChange = { senha = it },
                        label = { Text("Senha", color = Color(0xFF4F4E4E)) },
                        textStyle = TextStyle(color = Color(0xFF494949)),
                        shape = RoundedCornerShape(10.dp),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.NumberPassword
                        )
                    )

                    Button(
                        onClick = { navController.navigate(Screen.Home.route) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(60.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB60158))
                    ) {
                        Text(
                            text = "Cadastrar",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Text(
                        text = "Ou entre com",
                        color = Color(0xFF64002C),
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    Text(
                        text = "Já tem uma conta? Entrar",
                        color = Color(0xFF64002C),
                        fontWeight = FontWeight.Medium,
                        fontSize = 17.sp,
                        modifier = Modifier.clickable {
                            // Navega para a tela de cadastro
                            navController.navigate(Screen.Login.route)
                        }
                    )

                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CadastroPreview() {
    CadastroScreen(
        navController = NavController(LocalContext.current) // TODO()
    )
}