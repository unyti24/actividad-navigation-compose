package com.sena.navigationcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.sena.navigationcompose.data.LibrosData
import com.sena.navigationcompose.ui.screens.libros.DetalleLibroScreen

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

                    navController.navigate(
                        Routes.detalleLibro(libroId)
                    )
                }
            )
        }
        composable(
            route = Routes.DETALLE_LIBRO,
            arguments = listOf(
                navArgument("libroId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val libroId = backStackEntry.arguments?.getInt("libroId")

            val libro = LibrosData.libros.find {
                it.id == libroId
            }

            if (libro != null) {

                DetalleLibroScreen(
                    libro = libro,
                    onReservar = {
                        // Conectaremos la confirmación de reserva después.
                    }
                )
            }
        }
    }
}
