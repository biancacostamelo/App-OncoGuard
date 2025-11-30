package com.example.oncoguard.feature.perfil

import android.content.Context
import android.net.Uri
import android.util.Base64
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Request
import org.json.JSONObject
import kotlin.io.encoding.ExperimentalEncodingApi

class PerfilViewModel : ViewModel() {
    private val client = HttpClient (CIO)

    @OptIn(ExperimentalEncodingApi::class)
    fun enviarFotoParaCloudinary(
        uri: Uri,
        context: Context,
        uid: String,
        onComplete: (String?) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val inputStream = context.contentResolver.openInputStream(uri)
                val bytes = inputStream?.readBytes() ?: return@launch

                val base64 = Base64.encodeToString(bytes, Base64.DEFAULT)
                val base64Img = "data:image/jpg;base64,$base64"

                val client = OkHttpClient()

                val requestBody = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("file", base64Img)
                    .addFormDataPart("upload_preset", "ml_default")
                    .addFormDataPart("cloud_name", "dxevrm6zj")
                    .build()

                val request = Request.Builder()
                    .url("https://api.cloudinary.com/v1_1/dxevrm6zj/image/upload")
                    .post(requestBody)
                    .build()

                val response = client.newCall(request).execute()
                val responseBody = response.body?.string()

                Log.d("CLOUDINARY", "Resposta: $responseBody")

                val json = JSONObject(responseBody ?: "{}")
                val url = json.optString("secure_url", null)

                if (url != null) {
                    FirebaseFirestore.getInstance()
                        .collection("users")
                        .document(uid)
                        .update("foto", url)
                }

                withContext(Dispatchers.Main) {
                    onComplete(url)
                }

            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    onComplete(null)
                }
            }
        }
    }




}