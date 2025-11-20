package com.example.oncoguard.feature.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.oncoguard.R
import com.example.oncoguard.core.components.CustomTopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.composables.icons.lucide.ArrowLeft
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.shape.CircleShape
import com.composables.icons.lucide.ArrowUpFromLine
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.MessageSquare
import com.composables.icons.lucide.SendHorizontal
import com.composables.icons.lucide.Settings
import com.example.oncoguard.core.components.CustomBottomBar
import com.example.oncoguard.core.components.CustomBottomBar2
import com.example.oncoguard.feature.home.PinkCard
import com.example.oncoguard.feature.home.TextPrimary

@Composable
fun ChatScreen(
    navController: NavController,
    viewModel: ChatViewModel = viewModel(),
) {
    val messages by viewModel.messages.collectAsState()
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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                            .clickable { navController.popBackStack() })
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Chat AlicIA",
                        color = Color.White,
                        fontSize = 28.sp,
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


            // ==============================
            // TELA INICIAL (nenhuma mensagem)
            // ==============================
            if (messages.isEmpty()) {
                Spacer(modifier = Modifier.height(40.dp))

                Column(modifier = Modifier.padding(top = 50.dp)) {

                    Text(
                        text = "Como posso ajudar?",
                        color = Color.White,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        fontSize = 28.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 80.dp)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.aliciachat),
                        contentDescription = "AlicIA",
                        modifier = Modifier
                            .size(220.dp)
                            .align(Alignment.CenterHorizontally)
                            .background(Color(0xFFF2A7D7), shape = CircleShape)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                ChatInput(onSend = { viewModel.sendMessage(it) })
            }

            // ==============================
            // LISTA DE MENSAGENS
            // ==============================
            else {
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
                            horizontalArrangement = if (msg.isUser) Arrangement.End
                            else Arrangement.Start
                        ) {
                            Text(
                                text = msg.text,
                                color = Color.White,
                                modifier = Modifier
                                    .background(
                                        if (msg.isUser) Color(0x7A944073) else Color(0xFFB44E8A),
                                        shape = RoundedCornerShape(30.dp)
                                    )
                                    .padding(12.dp)
                            )
                        }
                    }
                }

                ChatInput(onSend = { viewModel.sendMessage(it) })
            }
        }
    }
}

// ---------------------------
// Campo de input estilizado
// ---------------------------
@Composable
fun ChatInput(onSend: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
            .border(
                width = 1.dp,
                color = Color.White,
                shape = RoundedCornerShape(40.dp)
            )
            .background(Color(0xFFB3649B), RoundedCornerShape(40.dp))
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = Lucide.ArrowUpFromLine,
            contentDescription = "Message Square",
            tint = Color.White
        )

        Spacer(modifier = Modifier.width(10.dp))

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            placeholder = { Text("Digite uma mensagem...", color = Color.White.copy(.6f)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFB44E8A),
                unfocusedBorderColor = Color(0xFFB44E8A),
                cursorColor = PinkCard,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Icon(
            imageVector = Lucide.SendHorizontal,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(28.dp)
                .padding(end = 6.dp)
                .clickable {
                    if (text.isNotBlank()) {
                        onSend(text)
                        text = ""
                    }
                })
    }
}

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    ChatScreen(
        navController = NavController(LocalContext.current)
    )
}


@Composable
fun CustomBottomBar4(navController: NavController) {
    // pega o padding do navigation bar (pode ser 0 em gesture nav)
    val navBarBottomPadding = WindowInsets.navigationBars
        .asPaddingValues()
        .calculateBottomPadding()

    // altura visível da barra "azul" (a parte arredondada)
    val visibleBarHeight = 60.dp

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                // Estende o Surface para cobrir a nav bar somando a padding
                .height(visibleBarHeight),
            color = Color(0xFFF2A7D7),
        ) {
        }
    }
}