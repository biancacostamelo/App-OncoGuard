package com.example.oncoguard.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.oncoguard.TelaONGS
import com.example.oncoguard.feature.auth.CadastroScreen
import com.example.oncoguard.feature.auth.LoginScreen
import com.example.oncoguard.feature.chat.ChatScreen
import com.example.oncoguard.feature.home.HomeScreen
import com.example.oncoguard.feature.home.TelaAcolhimento
import com.example.oncoguard.feature.home.TelaBemEstar
import com.example.oncoguard.feature.home.TelaDicas
import com.example.oncoguard.feature.home.TelaEsperanca
import com.example.oncoguard.feature.inicio.InicioScreen
import com.example.oncoguard.feature.perfil.ConfigScreen
import com.example.oncoguard.feature.perfil.EditarPerfilScreen
import com.example.oncoguard.feature.planos.PlanosScreen
import com.example.oncoguard.feature.splash.SplashScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Inicio : Screen("inicio")
    object Login : Screen("login")
    object Cadastro : Screen("cadastro")
    object ConfigScreen : Screen("ConfigScreen")
    object EditarPerfil: Screen("EditarPerfil")
    object Planos: Screen("Planos")
    object ChatScreen: Screen("ChatScreen")
    object  TelaDicas : Screen("TelaDicas")
    object  TelaBemEstar : Screen("TelaBemEstar")
    object  TelaAcolhimento : Screen("TelaAcolhimento")
    object  TelaEsperanca : Screen("TelaEsperanca")
    object  TelaONGS : Screen("TelaONGS")
}

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(
        // A função NavHost é responsável por gerenciar a navegação entre as telas
        navController = navController,
        startDestination = Screen.Home.route
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
        composable(Screen.EditarPerfil.route) {
            EditarPerfilScreen(navController)
        }
        composable(Screen.Planos.route) {
            PlanosScreen(navController)
        }
        composable(Screen.ChatScreen.route) {
            ChatScreen(navController)
        }
        composable (Screen.TelaDicas.route){
            TelaDicas(navController)
        }
        composable (Screen.TelaBemEstar.route){
            TelaBemEstar(navController)
        }
        composable (Screen.TelaAcolhimento.route){
            TelaAcolhimento(navController)
        }
        composable (Screen.TelaEsperanca.route){
            TelaEsperanca(navController)
        }
        composable (Screen.TelaONGS.route){
            TelaONGS(navController)
        }

    }
}