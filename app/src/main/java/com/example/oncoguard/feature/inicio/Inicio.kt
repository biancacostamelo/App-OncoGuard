package com.example.oncoguard.feature.inicio

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.oncoguard.R
import com.example.oncoguard.core.navigation.Screen

@Composable
fun InicioScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFDAF3))
            .padding(horizontal = 41.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Text(
                text = "Bem-Vinda(o)!",
                color = Color(0xFFB60158),
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Estamos aqui para caminhar com você.",
                color = Color(0xFFB60158),
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal
            )
        }

        Spacer(modifier = Modifier.height(34.dp))

        Text(
            text = "Faça seu login ou cadastre-se.",
            color = Color(0xFFB60158),
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )

        Image(
            painter = painterResource(R.drawable.aliciatelainicio),
            contentDescription = "alicia tela inicio",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(406.dp)
                .graphicsLayer(
                    scaleX = -1f
                )
        )
        Column {
            Button(
                onClick = { navController.navigate(Screen.Login.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(60.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB60158))
            ) { Text("Entrar" , color = Color(0xFFFFFFFF), fontWeight = FontWeight.Bold) }

            Spacer(modifier = Modifier.height(14.dp))

            Button(
                onClick = { navController.navigate(Screen.Cadastro.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .border(1.dp, Color(0xFFB60158), shape = RoundedCornerShape(60.dp)), // 👈 Borda adicionada
                shape = RoundedCornerShape(60.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFDAF3))
            ) { Text("Criar Conta" , color = Color(0xFFB60158), fontWeight = FontWeight.Bold) }
        }



        @Composable
        fun TextButtonExample(onClick: () -> Unit) {
            TextButton(
                onClick = { onClick() }) {
                Text("Text Button")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun InicioPreview() {
    InicioScreen(
        navController = NavController(LocalContext.current) // TODO()
    )
}