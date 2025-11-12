package com.example.oncoguard.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.oncoguard.core.navigation.AppNavigation
import com.example.oncoguard.ui.theme.OncoGuardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OncoGuardTheme {
                val navController = rememberNavController()
                AppNavigation(navController = navController)
            }
        }
    }
}