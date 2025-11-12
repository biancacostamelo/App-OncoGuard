package com.example.oncoguard.core.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String,
    navigationIcon: ImageVector,
    showBackButton: Boolean,
    navController: NavController? = null,
    titleColor: Color = Color.White
){
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor =  Color(0xFF54A1E0),
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        title = {
            Text(title,  color = titleColor)
        },
        navigationIcon = {
            if (showBackButton && navController != null) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Voltar",
                        tint = Color.White
                    )
                }
            } else {
                Icon(
                    imageVector = navigationIcon,
                    contentDescription = title
                )
            }
        }
    )
}