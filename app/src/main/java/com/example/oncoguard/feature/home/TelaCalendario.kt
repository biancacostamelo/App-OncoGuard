package com.example.oncoguard.feature.home

import android.app.TimePickerDialog
import android.widget.CalendarView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.oncoguard.core.components.CustomBottomBar
import com.example.oncoguard.core.components.CustomTopAppBar
import com.example.oncoguard.R
import java.util.*

data class Tarefa(
    var titulo: String,
    var horario: String,
    var lembrar: Boolean
)

@Composable
fun TelaCalendario(navController: NavController) {

    val tarefas = remember { mutableStateMapOf<String, MutableList<Tarefa>>() }
    var dataSelecionada by remember { mutableStateOf("") }
    var mostrarDialog by remember { mutableStateOf(false) }
    var novaTarefa by remember { mutableStateOf("") }
    var horario by remember { mutableStateOf("") }
    var lembrar by remember { mutableStateOf(false) }
    var tarefaEditando by remember { mutableStateOf<Tarefa?>(null) }
    val context = LocalContext.current

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
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color(0xFF54A1E0))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.imgcalend),
                        contentDescription = "Personagem",
                        modifier = Modifier.size(110.dp)
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = "Calendário",
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF54A1E0)),
                contentAlignment = Alignment.TopCenter
            ) {

                Surface(
                    shape = RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp),
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(33.dp),
                        verticalArrangement = Arrangement.Top
                    ) {

                        Surface(
                            shape = RoundedCornerShape(30.dp),
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(350.dp)
                        ) {
                            AndroidView(
                                factory = { ctx ->
                                    CalendarView(ctx).apply {
                                        setBackgroundColor(android.graphics.Color.parseColor("#F2A1D9"))
                                        setSelectedWeekBackgroundColor(
                                            android.graphics.Color.parseColor("#B60158")
                                        )
                                        setSelectedDateVerticalBar(
                                            android.graphics.Color.parseColor("#B60158")
                                        )
                                        setOnDateChangeListener { _, year, month, dayOfMonth ->
                                            dataSelecionada = String.format(
                                                "%02d/%02d/%04d",
                                                dayOfMonth,
                                                month + 1,
                                                year
                                            )
                                        }
                                    }
                                },
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        Spacer(Modifier.height(20.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()

                        ) {

                            Text(
                                text = "Tarefas de $dataSelecionada",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFB60158)
                            )

                            Spacer(Modifier.height(10.dp))

                            val lista = tarefas[dataSelecionada] ?: mutableListOf()

                            if (lista.isEmpty()) {
                                Text("Nenhuma tarefa adicionada.", color = Color.Gray)
                            } else {
                                Column {
                                    lista.forEach { tarefa ->

                                        Card(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 4.dp),
                                            colors = CardDefaults.cardColors(
                                                containerColor = Color(0xFFEFF7FF)
                                            )
                                        ) {

                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(16.dp),
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {

                                                Column {
                                                    Text(tarefa.titulo, fontSize = 19.sp, color = Color.DarkGray)
                                                    Text("Horário: ${tarefa.horario}", fontSize = 15.sp, color = Color.DarkGray)
                                                    if (tarefa.lembrar) {
                                                        Text("Lembrar-me: Ativado", fontSize = 14.sp, color = Color.DarkGray)
                                                    }
                                                }

                                                IconButton(onClick = {
                                                    tarefaEditando = tarefa
                                                    novaTarefa = tarefa.titulo
                                                    horario = tarefa.horario
                                                    lembrar = tarefa.lembrar
                                                    mostrarDialog = true
                                                }) {
                                                    Icon(
                                                        imageVector = Icons.Default.Edit,
                                                        contentDescription = "Editar",
                                                        tint = Color(0xFF1E88E5)
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            Spacer(Modifier.height(20.dp))

                            // BOTÃO DE ADICIONAR TAREFA
                            Button(
                                onClick = {
                                    tarefaEditando = null
                                    novaTarefa = ""
                                    horario = ""
                                    lembrar = false
                                    mostrarDialog = true
                                },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFB60158),
                                    contentColor = Color.White
                                )
                            ) {
                                Text("Adicionar tarefa")
                            }

                            // DIALOG
                            if (mostrarDialog) {

                                AlertDialog(
                                    onDismissRequest = { mostrarDialog = false },

                                    confirmButton = {
                                        TextButton(onClick = {

                                            if (novaTarefa.isNotEmpty()) {

                                                if (tarefaEditando == null) {
                                                    tarefas.getOrPut(dataSelecionada) { mutableListOf() }
                                                        .add(
                                                            Tarefa(
                                                                titulo = novaTarefa,
                                                                horario = horario.ifEmpty { "Sem horário" },
                                                                lembrar = lembrar
                                                            )
                                                        )
                                                } else {
                                                    tarefaEditando!!.titulo = novaTarefa
                                                    tarefaEditando!!.horario =
                                                        horario.ifEmpty { "Sem horário" }
                                                    tarefaEditando!!.lembrar = lembrar
                                                }
                                            }

                                            mostrarDialog = false
                                        }) {
                                            Text("Salvar")
                                        }
                                    },

                                    dismissButton = {
                                        TextButton(onClick = { mostrarDialog = false }) {
                                            Text("Cancelar")
                                        }
                                    },

                                    title = {
                                        Text(if (tarefaEditando == null) "Nova tarefa" else "Editar tarefa")
                                    },

                                    text = {
                                        Column {

                                            TextField(
                                                value = novaTarefa,
                                                onValueChange = { novaTarefa = it },
                                                placeholder = { Text("Descrição da tarefa") }
                                            )

                                            Spacer(Modifier.height(16.dp))

                                            Button(
                                                onClick = {
                                                    val cal = Calendar.getInstance()
                                                    val timePicker = TimePickerDialog(
                                                        context,
                                                        { _, h, m ->
                                                            horario = String.format("%02d:%02d", h, m)
                                                        },
                                                        cal.get(Calendar.HOUR_OF_DAY),
                                                        cal.get(Calendar.MINUTE),
                                                        true
                                                    )
                                                    timePicker.show()
                                                },
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = Color(0xFFB60158),
                                                    contentColor = Color.White
                                                )
                                            ) {
                                                Text(
                                                    if (horario.isEmpty())
                                                        "Escolher horário"
                                                    else
                                                        "Horário: $horario"
                                                )
                                            }

                                            Spacer(Modifier.height(16.dp))

                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Switch(
                                                    checked = lembrar,
                                                    onCheckedChange = { lembrar = it },
                                                    colors = SwitchDefaults.colors(
                                                        checkedThumbColor = Color(0xFFB60158),
                                                        checkedTrackColor = Color(0xFFB60158).copy(alpha = 0.4f),
                                                        uncheckedThumbColor = Color.Gray,
                                                        uncheckedTrackColor = Color.LightGray
                                                    )
                                                )

                                                Text(
                                                    "Lembrar-me",
                                                    color = Color(0xFFB60158)
                                                )
                                            }

                                        }
                                    })
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarioPreview() {
    TelaCalendario(
        navController = NavController(LocalContext.current) // TODO()
    )
}