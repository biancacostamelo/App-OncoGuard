package com.example.oncoguard.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.oncoguard.core.navigation.AppNavigation
import com.example.oncoguard.ui.theme.OncoGuardTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        FirebaseApp.initializeApp(this)
        setContent {
            OncoGuardTheme {
                val navController = rememberNavController()
                AppNavigation(navController = navController)
            }
        }
    }
}