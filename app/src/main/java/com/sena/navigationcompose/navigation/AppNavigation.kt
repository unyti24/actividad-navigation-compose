package com.sena.navigationcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sena.navigationcompose.data.ReservasData
import com.sena.navigationcompose.data.LibrosData
import com.sena.navigationcompose.ui.screens.home.HomeScreen
import com.sena.navigationcompose.ui.screens.libros.ListaLibrosScreen
import com.sena.navigationcompose.ui.screens.libros.DetalleLibroScreen
import com.sena.navigationcompose.ui.screens.libros.ConfirmacionReservaScreen
import com.sena.navigationcompose.ui.screens.reservas.MisReservasScreen
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
                },
                onVerReservas = {
                    navController.navigate(Routes.MIS_RESERVAS)
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

        // Pantalla de detalle del libro
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

                        // Registrar la reserva
                        ReservasData.agregarReserva(libro)

                        // Ir a la pantalla de confirmación
                        navController.navigate(
                            Routes.confirmacionReserva(libro.id)
                        )
                    }
                )
            }
        }

        // Pantalla de confirmación de reserva
        composable(
            route = Routes.CONFIRMACION_RESERVA,
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

                ConfirmacionReservaScreen(
                    libro = libro,
                    onVolverInicio = {

                        navController.navigate(Routes.HOME) {
                            popUpTo(Routes.HOME) {
                                inclusive = false
                            }
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
        // Pantalla de Mis reservas
        composable(Routes.MIS_RESERVAS) {

            MisReservasScreen(
                onVerDetalle = { reservaId ->

                    navController.navigate(
                        "detalle_reserva/$reservaId"
                    )
                }
            )
        }
    }
}