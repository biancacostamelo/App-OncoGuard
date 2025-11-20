package com.example.oncoguard.feature.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class ChatMessage(
    val text: String,
    val isUser: Boolean // true = usuário, false = IA
)

class ChatViewModel : ViewModel() {

    // --- CLIENTE HTTP (mantido igual) ---
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    // --- SEU WEBHOOK (mantido igual) ---
    private val webhookUrl = "https://oncoguard.app.n8n.cloud/webhook/chat"

    // --- LISTA DE MENSAGENS (agora com ChatMessage) ---
    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages

    // --- FUNÇÃO DE ENVIO ---
    fun sendMessage(text: String) {
        if (text.isBlank()) return

        // adiciona mensagem do usuário imediatamente
        _messages.update { it + ChatMessage(text, true) }

        viewModelScope.launch {
            try {
                val response: Map<String, String> = client.post(webhookUrl) {
                    contentType(ContentType.Application.Json)
                    setBody(mapOf("chatInput" to text))
                }.body()

                val reply = response["output"] ?: "Erro: sem resposta"

                // adiciona resposta da IA
                _messages.update { it + ChatMessage(reply, false) }

            } catch (e: Exception) {
                _messages.update {
                    it + ChatMessage("Erro ao enviar mensagem: ${e.message}", false)
                }
            }
        }
    }
}
