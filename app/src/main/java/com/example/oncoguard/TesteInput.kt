import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun ChatInputBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color(0xFFB5739E), shape = RoundedCornerShape(50.dp)) // Fundo roxo arredondado
            .padding(horizontal = 12.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Ícone da esquerda
        Icon(
            imageVector = Icons.Default.Share, // ou outro ícone que quiser
            contentDescription = "Compartilhar",
            tint = Color.White,
            modifier = Modifier.size(22.dp)
        )

        // Campo de texto (input)
        var message by remember { mutableStateOf("") }
        TextField(
            value = message,
            onValueChange = { message = it },
            placeholder = { Text("Digite algo...", color = Color.White.copy(alpha = 0.7f)) },
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        )

        // Ícone de microfone/desativar som
        Icon(
            imageVector = Icons.Default.Call, // ícone do meio
            contentDescription = "Microfone desligado",
            tint = Color.White,
            modifier = Modifier.size(22.dp)
        )

        // Ícone de enviar (setinha)
        Icon(
            imageVector = Icons.Default.PlayArrow,
            contentDescription = "Enviar",
            tint = Color.White,
            modifier = Modifier.size(22.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    ChatInputBar()
}
