package com.example.oncoguard.feature.comunidade

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HistoriaViewModel: ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    private val _historias = MutableStateFlow<List<Map<String, String>>>(emptyList())
    val historias: StateFlow<List<Map<String, String>>> = _historias

    init {
        // Atualiza automaticamente quando algo muda no Firestore
        db.collection("historias")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, _ ->
                val lista = snapshot?.documents?.map { it.data as Map<String, String> } ?: emptyList()
                _historias.value = lista
            }
    }


    fun getNomeDoUsuario(uid: String, onResult: (String) -> Unit) {
        FirebaseFirestore.getInstance()
            .collection("users")
            .document(uid)
            .get()
            .addOnSuccessListener { doc ->
                val nome = doc.getString("nome") ?: "Sem nome"
                onResult(nome)
            }
    }


    fun enviarHistoria(historia: String, nome: String, uid: String, fotoUser: String?) {
        val dados = mapOf(
            "uid" to uid,
            "nome" to nome,
            "historia" to historia,
            "foto" to (fotoUser ?: ""),
            "timestamp" to FieldValue.serverTimestamp() // <-- salva a hora do servidor
        )

        db.collection("historias").add(dados)

    }
}