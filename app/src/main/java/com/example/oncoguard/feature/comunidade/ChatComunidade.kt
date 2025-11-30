package com.example.oncoguard.feature.comunidade

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.ArrowLeft
import com.composables.icons.lucide.ArrowUpFromLine
import com.composables.icons.lucide.SendHorizontal
import com.composables.icons.lucide.Settings
import com.composables.icons.lucide.User

import com.example.oncoguard.feature.chat.CustomBottomBar4
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun ChatComunidade(uid: String, navController: NavController) {
    val messages = remember { mutableStateListOf<String>() }
    var text by remember { mutableStateOf("") }

    val db = FirebaseFirestore.getInstance()

    var nome by remember { mutableStateOf("") }
    var foto by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(uid) {
        db.collection("users").document(uid).get()
            .addOnSuccessListener { doc ->
                nome = doc.getString("nome") ?: "Sem nome"
                foto = doc.getString("foto")
            }
    }

    Scaffold(
        bottomBar = { CustomBottomBar4(navController = navController) },
        modifier = Modifier.background(Color(0xFFF2A7D7)),
        contentWindowInsets = WindowInsets.systemBars
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF2A7D7)),
        ) {

            // CABEÇALHO
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp, horizontal = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Lucide.ArrowLeft,
                        contentDescription = "Voltar",
                        tint = Color.White,
                        modifier = Modifier
                            .size(26.dp)
                            .clickable { navController.popBackStack() }
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    AsyncImage(
                        model = foto,
                        contentDescription = "Foto do usuário",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(45.dp)
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    Text(
                        text = nome,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Icon(
                    imageVector = Lucide.Settings,
                    contentDescription = "Configurações",
                    tint = Color(0xFFB60158),
                    modifier = Modifier.size(30.dp)
                )
            }


            // LISTA DE MENSAGENS
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.Bottom
            ) {

                items(messages) { msg ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = msg,
                            color = Color.White,
                            modifier = Modifier
                                .background(
                                    color = Color(0x7A944073),
                                    shape = RoundedCornerShape(30.dp)
                                )
                                .padding(12.dp)
                        )
                    }
                }
            }


            // CAMPO DE DIGITAR + BOTÃO ENVIAR
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 10.dp)
                    .border(
                        width = 1.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(40.dp)
                    )
                    .background(Color(0xFFB3649B), RoundedCornerShape(40.dp))
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Spacer(modifier = Modifier.width(10.dp))

                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    placeholder = { Text("Digite uma mensagem...", color = Color.White.copy(.6f)) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        cursorColor = Color.White,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Icon(
                    imageVector = Lucide.SendHorizontal,
                    contentDescription = "Enviar",
                    tint = Color.White,
                    modifier = Modifier
                        .size(28.dp)
                        .padding(end = 6.dp)
                        .clickable {
                            if (text.isNotBlank()) {
                                messages.add(text)  // <--- AGORA FUNCIONA
                                text = ""
                            }
                        }
                )
            }
        }
    }
}
