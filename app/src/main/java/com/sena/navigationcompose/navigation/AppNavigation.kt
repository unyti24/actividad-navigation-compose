package com.sena.navigationcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.sena.navigationcompose.ui.screens.home.HomeScreen
import com.sena.navigationcompose.ui.screens.libros.ListaLibrosScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {

        // Pantalla de inicio
        composable(Routes.HOME) {

            HomeScreen(
                onVerLibros = {
                    navController.navigate(Routes.LISTA_LIBROS)
                }
            )
        }

        // Pantalla de lista de libros
        composable(Routes.LISTA_LIBROS) {

            ListaLibrosScreen(
                onVerDetalle = { libroId ->
                    // Conectaremos el detalle del libro
                    // cuando desarrollemos esa pantalla.
                }
            )
        }
    }
}
