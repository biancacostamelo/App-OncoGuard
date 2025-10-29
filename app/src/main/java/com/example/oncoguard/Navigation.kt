package com.example.oncoguard

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Inicio : Screen("inicio")
    object Login : Screen("login")
    object  Cadastro : Screen("cadastro")
    object  ConfigScreen : Screen("ConfigScreen")
}

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(
        // A função NavHost é responsável por gerenciar a navegação entre as telas
        navController = navController,
        startDestination = Screen.ConfigScreen.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Inicio.route) {
            InicioScreen(navController)
        }
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(Screen.Cadastro.route) {
            CadastroScreen(navController)
        }
        composable(Screen.ConfigScreen.route) {
            ConfigScreen(navController)
        }
    }
}