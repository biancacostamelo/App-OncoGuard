package com.example.oncoguard.feature.perfil

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.oncoguard.core.components.CustomBottomBar
import com.example.oncoguard.core.components.CustomTopAppBar
import com.example.oncoguard.core.navigation.Screen
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore


@Composable
fun EditarPerfilScreen(navController: NavController, vm: PerfilViewModel = viewModel()) {
    val context = LocalContext.current
    val uid = Firebase.auth.currentUser?.uid ?: return
    var fotoUrl by remember { mutableStateOf<String?>(null) }

    var nome by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var aniversario by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        FirebaseFirestore.getInstance()
            .collection("users")
            .document(uid)
            .get()
            .addOnSuccessListener { doc ->
                nome = doc.getString("nome") ?: ""
                telefone = doc.getString("telefone") ?: ""
                aniversario = doc.getString("aniversario") ?: ""
                email = doc.getString("email") ?: ""
                fotoUrl = doc.getString("foto")
            }
    }

    // Launcher pra escolher imagem
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            vm.enviarFotoParaCloudinary(uri, context, uid) { novaUrl ->
                fotoUrl = novaUrl
            }

        }
    }

    Log.d("FIRESTORE", "fotinha $fotoUrl")

    Scaffold(
        bottomBar = { CustomBottomBar(navController = navController) },
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            CustomTopAppBar(
                title = "Editar Perfil",
                navigationIcon = Icons.Default.Info,
                showBackButton = true,
                navController = navController
            )
        }
    ) { paddingValues ->
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFFEFDF9))
                .verticalScroll(scrollState)
                .consumeWindowInsets(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(bottomEnd = 100.dp, bottomStart = 100.dp))
                    .width(214.dp)
                    .background(Color(0xFF54A1E0))
                    .padding(start = 28.dp, end = 28.dp, top = 0.dp, bottom = 28.dp)
                    .clickable { launcher.launch("image/*") }
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
                    if (fotoUrl != null) {
                        AsyncImage(
                            model = fotoUrl,
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
                                .background(Color.LightGray),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "Adicionar\nfoto",
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 40.dp)
                    .padding(top = 40.dp, bottom = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                OutlinedTextField(
                    value = nome,
                    onValueChange = { nome = it },
                    label = { Text("Nome", color = Color(0xFF4F4E4E)) },
                    shape = RoundedCornerShape(10.dp),
                    textStyle = TextStyle(color = Color(0xFF494949)),
                )

                OutlinedTextField(
                    value = aniversario,
                    onValueChange = { aniversario = it },
                    label = { Text("Aniversário", color = Color(0xFF4F4E4E)) },
                    shape = RoundedCornerShape(10.dp),
                    textStyle = TextStyle(color = Color(0xFF494949)),
                )

                OutlinedTextField(
                    value = telefone,
                    onValueChange = { telefone = it },
                    label = { Text("Telefone", color = Color(0xFF4F4E4E)) },
                    shape = RoundedCornerShape(10.dp),
                    textStyle = TextStyle(color = Color(0xFF494949)),
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("E-mail", color = Color(0xFF4F4E4E)) },
                    shape = RoundedCornerShape(10.dp),
                    textStyle = TextStyle(color = Color(0xFF494949)),
                )

                Button(
                    onClick = {
                        salvarPerfil(
                            nome = nome,
                            telefone = telefone,
                            aniversario = aniversario,
                            email = email,
                            onSuccess = {
                                Toast.makeText(context, "Dados atualizados!", Toast.LENGTH_SHORT)
                                    .show()
                                navController.navigate(Screen.Home.route)
                            },
                            onError = {
                                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                            }
                        )
                    },
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

fun salvarPerfil(
    nome: String,
    telefone: String,
    aniversario: String,
    email: String,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
) {
    val db = FirebaseFirestore.getInstance()
    val uid = Firebase.auth.currentUser?.uid ?: return

    val dadosAtualizados = mapOf(
        "nome" to nome,
        "telefone" to telefone,
        "aniversario" to aniversario,
        "email" to email
    )

    db.collection("users").document(uid)
        .update(dadosAtualizados)
        .addOnSuccessListener {

            db.collection("historias")
                .whereEqualTo("uid", uid)
                .get()
                .addOnSuccessListener { result ->
                    for (doc in result) {
                        doc.reference.update("nome", nome)
                    }
                    onSuccess()
                }
        }
        .addOnFailureListener {
            Log.e("UPDATE", "Erro ao atualizar usuario", it)
        }

}


@Preview(showBackground = true)
@Composable
fun EditPreview() {
    EditarPerfilScreen(
        navController = NavController(LocalContext.current) // TODO()
    )
}