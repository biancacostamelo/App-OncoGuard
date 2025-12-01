package com.example.oncoguard.feature.comunidade

import android.net.Uri
import android.util.Log
import androidx.navigation.NavController

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Pencil
import com.composables.icons.lucide.Settings
import com.composables.icons.lucide.Trash
import com.example.oncoguard.R
import com.example.oncoguard.core.components.CustomBottomBar
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

val PinkHeader = Color(0xFFFEE8FC)
val PinkCard = Color(0xFFC71585)
val BlueFooter = Color(0xFF64B5F6)
val TextPrimary = Color(0xFF333333)

val auth = Firebase.auth
val usuario = auth.currentUser
val nomeUser = usuario?.displayName ?: "Sem nome"
val uid = usuario?.uid ?: ""

@Composable

fun TelaComunidade(navController: NavController, vm: HistoriaViewModel = viewModel()) {
    val historias by vm.historias.collectAsState()

    Scaffold(
        bottomBar = { CustomBottomBar(navController = navController) },
        contentWindowInsets = WindowInsets.safeDrawing
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(PinkHeader)
                .padding(paddingValues)
        ) {
            HeaderComunidade(navController = navController)

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    InputHistoria()
                    Spacer(modifier = Modifier.height(16.dp))
                }

                items(historias) { item ->
                    val donoHistoria = item["uid"]
                    var showDeleteDialog by remember { mutableStateOf(false) }

                    var showEditDialog by remember { mutableStateOf(false) }
                    var editText by remember { mutableStateOf(item["historia"] as? String ?: "") }

                    // uid = currentUid
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(PinkCard)
                            .clickable {
                                val uid = item["uid"] ?: return@clickable
                                val encoded = Uri.encode(uid as String?)
                                navController.navigate("TelaPerfilUsuario/$encoded")
                            }
                            .padding(20.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Box(
                            modifier = Modifier
                                .size(70.dp)
                                .clip(CircleShape)
                                .background(Color.White)
                                .padding(4.dp)
                                .clip(CircleShape)
                        ) {
                            AsyncImage(
                                model = item["foto"],
                                contentDescription = item["nome"] as? String ?: "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(Modifier.fillMaxWidth(0.76f)) {
                            Text(
                                text = item["nome"] as? String ?: "",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                item["historia"] as? String ?: "",
                                color = Color.White.copy(alpha = 0.9f),
                                fontSize = 16.sp,
                                lineHeight = 22.sp,
                            )
                        }
                        if (uid == donoHistoria) {
                            Row() {
                                Icon(
                                    imageVector = Lucide.Pencil,
                                    contentDescription = "Editar",
                                    tint = Color(0xFFFFFFFF),
                                    modifier = Modifier
                                        .clickable {
                                            showEditDialog = true
                                        }
                                )

                                Spacer(modifier = Modifier.width(12.dp))

                                Icon(
                                    imageVector = Lucide.Trash,
                                    contentDescription = "Deletar",
                                    tint = Color(0xFFFFFFFF),
                                    modifier = Modifier
                                        .clickable {
                                            showDeleteDialog = true
                                        }
                                )
                            }
                        }

                        if (showDeleteDialog) {
                            AlertDialog(
                                onDismissRequest = { showDeleteDialog = false },
                                title = { Text("Confirmar exclusão") },
                                text = { Text("Tem certeza que deseja excluir esta história?") },
                                confirmButton = {
                                    Text(
                                        text = "Excluir",
                                        color = Color.Red,
                                        modifier = Modifier.clickable {
                                            val id = item["id"] as? String ?: ""
                                            vm.deletarHistoria(id)
                                            showDeleteDialog = false
                                        }
                                    )
                                },
                                dismissButton = {
                                    Text(
                                        text = "Cancelar",
                                        modifier = Modifier.clickable {
                                            showDeleteDialog = false
                                        }
                                    )
                                }
                            )
                        }

                        if (showEditDialog) {
                            AlertDialog(
                                onDismissRequest = { showEditDialog = false },
                                title = { Text("Editar história") },
                                text = {
                                    Column {
                                        Text("Altere o texto como desejar:")
                                        Spacer(Modifier.height(10.dp))
                                        OutlinedTextField(
                                            value = editText,
                                            onValueChange = { editText = it },
                                            modifier = Modifier.fillMaxWidth(),
                                            placeholder = { Text("Escreva sua história...") },
                                            maxLines = 5
                                        )
                                    }
                                },
                                confirmButton = {
                                    Text(
                                        text = "Salvar",
                                        color = Color(0xFF54A1E0),
                                        modifier = Modifier.clickable {
                                            val id = item["id"] as? String ?: ""
                                            vm.atualizarHistoria(id, editText)
                                            showEditDialog = false
                                        }
                                    )
                                },
                                dismissButton = {
                                    Text(
                                        text = "Cancelar",
                                        modifier = Modifier.clickable {
                                            showEditDialog = false
                                        }
                                    )
                                }
                            )
                        }

                    }
                }

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Veja mais >",
                            color = PinkCard,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            modifier = Modifier.clickable { }
                        )
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }
}

@Composable
fun HeaderComunidade(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(PinkHeader)
            .padding(top = 8.dp, bottom = 24.dp, start = 16.dp, end = 16.dp)
    ) {
        Text(
            text = "< Voltar",
            color = Color(0xFFB60158),
            fontSize = 18.sp,
            modifier = Modifier.clickable { navController.popBackStack() }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Comunidade",
            color = PinkCard,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 32.sp
        )
        Text(
            text = "Compartilhe sua ideia! Escreva aqui e inspire outros.",
            color = TextPrimary,
            fontSize = 14.sp
        )
    }
}

@Composable
fun InputHistoria(vm: HistoriaViewModel = viewModel()) {
    val historias by vm.historias.collectAsState()

    val uid = Firebase.auth.currentUser?.uid ?: ""
    var nomeUser by remember { mutableStateOf("Carregando...") }
    var fotoUser by remember { mutableStateOf<String?>(null) }

// Carregar nome + foto
    LaunchedEffect(uid) {
        val userDoc = FirebaseFirestore.getInstance()
            .collection("users")
            .document(uid)
            .get()
            .await()

        nomeUser = userDoc.getString("nome") ?: "Usuário"
        fotoUser = userDoc.getString("foto")
    }

    var textoHistoria by remember { mutableStateOf("") }
    Log.d("AUTH_TEST", "historias: ${historias}")

    OutlinedTextField(
        value = textoHistoria,
        onValueChange = { textoHistoria = it },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .clip(RoundedCornerShape(25.dp)),
        placeholder = {
            Text(
                "Escreva sua história...",
                color = Color(0xFFBCBCBC)
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Send,
                contentDescription = "Enviar",
                tint = PinkCard,
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        if (textoHistoria.isNotBlank()) {
                            vm.enviarHistoria(textoHistoria, nomeUser, uid, fotoUser)

                            textoHistoria = ""
                        }
                        textoHistoria = ""

                    }
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = PinkCard,
            unfocusedBorderColor = PinkCard.copy(alpha = 0.5f),
            cursorColor = PinkCard,
            focusedTextColor = TextPrimary,
            unfocusedTextColor = Color.Gray
        ),
        shape = RoundedCornerShape(25.dp),
        textStyle = LocalTextStyle.current.copy(fontSize = 16.sp),
        singleLine = true
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewTelaComunidade() {
    val navController = rememberNavController()
    TelaComunidade(navController = navController)
}