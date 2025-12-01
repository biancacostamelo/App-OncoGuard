package com.example.oncoguard.feature.comunidade

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HistoriaViewModel: ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    // no topo da classe
    private val _historias = MutableStateFlow<List<Map<String, Any>>>(emptyList())
    val historias: StateFlow<List<Map<String, Any>>> = _historias

    init {
        db.collection("historias")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, _ ->
                val lista = snapshot?.documents?.map { doc ->
                    val data = doc.data?.toMutableMap() ?: mutableMapOf<String, Any>()
                    data["id"] = doc.id
                    data
                } ?: emptyList()
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

    fun deletarHistoria(id: String) {
        if (id.isBlank()) return

        db.collection("historias")
            .document(id)
            .delete()
            .addOnSuccessListener {
                Log.d("HISTORIA", "Deletada: $id")
            }
            .addOnFailureListener { e ->
                Log.e("HISTORIA", "Erro ao deletar $id", e)
            }
    }

    fun atualizarHistoria(id: String, novoTexto: String, onResult: (Boolean) -> Unit = {}) {
        if (id.isBlank()) {
            onResult(false)
            return
        }

        val db = FirebaseFirestore.getInstance()

        db.collection("historias")
            .document(id)
            .update(
                mapOf(
                    "historia" to novoTexto,
                    "timestamp" to com.google.firebase.Timestamp.now()
                )
            )
            .addOnSuccessListener {
                onResult(true)
            }
            .addOnFailureListener {
                onResult(false)
            }
    }


}