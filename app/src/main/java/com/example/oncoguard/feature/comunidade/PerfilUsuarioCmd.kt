package com.example.oncoguard.feature.comunidade

import android.R
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.MessageSquare
import com.composables.icons.lucide.Mic
import com.example.oncoguard.core.components.CustomBottomBar
import com.example.oncoguard.core.components.CustomTopAppBar
import com.example.oncoguard.core.navigation.Screen
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun TelaPerfilUsuario(uid: String, navController: NavController) {
    val db = FirebaseFirestore.getInstance()

    var nome by remember { mutableStateOf("") }
    var foto by remember { mutableStateOf<String?>(null) }
    var aniversario by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    LaunchedEffect(uid) {
        db.collection("users").document(uid).get()
            .addOnSuccessListener { doc ->
                nome = doc.getString("nome") ?: "Sem nome"
                foto = doc.getString("foto")
                aniversario = doc.getString("aniversario") ?: "Não informado"
                telefone = doc.getString("telefone") ?: "Não informado"
                email = doc.getString("email") ?: "Não informado"
            }
    }

    Scaffold(
        bottomBar = { CustomBottomBar(navController = navController) },
        contentWindowInsets = WindowInsets.safeDrawing,
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
                .background(Color(0xFFFEFDF9))
                .consumeWindowInsets(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(bottomEnd = 100.dp, bottomStart = 100.dp))
                    .width(214.dp)
                    .background(Color(0xFF54A1E0))
                    .padding(start = 28.dp, end = 28.dp, top = 0.dp, bottom = 20.dp)
            ) {
                Box(
                    modifier = Modifier
                        .height(155.dp)
                        .width(155.dp)
                        .clip(RoundedCornerShape(150.dp))
                        .background(Color(0xFFB60158))
                        .padding(15.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (foto != null) {
                        AsyncImage(
                            model = foto,
                            contentDescription = "Foto do usuário",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(CircleShape)
                                .fillMaxSize()
                        )

                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape)
                                .background(androidx.compose.ui.graphics.Color.LightGray),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "Sem Foto",
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                Text(nome, fontSize = 28.sp, fontWeight = FontWeight.SemiBold, color = Color.DarkGray)

                Spacer(Modifier.height(25.dp))

                InfoRow(label = "Aniversário", value = aniversario)
                InfoRow(label = "Telefone", value = telefone)
                InfoRow(label = "E-mail", value = email)

                Spacer(Modifier.height(20.dp))

                Box(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color(0x9AB60158),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clip(RoundedCornerShape(10.dp))
                        .padding(20.dp)
                        .clickable{
                            val encoded = Uri.encode(uid)
                            navController.navigate("ChatComunidade/$encoded")
                        }
                ) {
                    Icon(
                        imageVector = Lucide.MessageSquare,
                        contentDescription = "Message Square",
                        tint = Color(0xFFB60158),
                    )
                }
            }
        }
    }


}

@Composable
fun InfoRow(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(label, fontWeight = FontWeight.SemiBold, color = Color.DarkGray)
        Text(value, fontSize = 18.sp, color = Color.DarkGray)
        Spacer(Modifier.height(12.dp))
    }
}
