package com.sena.navigationcompose.navigation


import com.sena.navigationcompose.ui.screens.reservas.ConfirmacionRenovacionScreen
import com.sena.navigationcompose.ui.screens.reservas.RenovarReservaScreen
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
import com.sena.navigationcompose.ui.screens.reservas.DetalleReservaScreen
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
        // Pantalla de detalle de reserva
        composable(
            route = Routes.DETALLE_RESERVA,
            arguments = listOf(
                navArgument("reservaId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val reservaId = backStackEntry.arguments?.getInt("reservaId")

            val reserva = reservaId?.let {
                ReservasData.obtenerReserva(it)
            }

            if (reserva != null) {

                DetalleReservaScreen(
                    reserva = reserva,
                    onRenovar = {
                        navController.navigate("renovar_reserva/${reserva.id}")
                    },
                    onCancelar = {
                        // Más adelante conectaremos la cancelación
                    },
                    onVolver = {
                        navController.popBackStack()
                    }
                )

            } else {
                androidx.compose.material3.Text(
                    text = "Reserva no encontrada"
                )
            }
        }
        // Pantalla para renovar reserva
        composable(
            route = Routes.RENOVAR_RESERVA,
            arguments = listOf(
                navArgument("reservaId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val reservaId = backStackEntry.arguments?.getInt("reservaId")

            val reserva = reservaId?.let {
                ReservasData.obtenerReserva(it)
            }

            if (reserva != null) {

                RenovarReservaScreen(
                    reserva = reserva,

                    onConfirmar = {
                        ReservasData.renovarReserva(reserva.id)

                        navController.navigate(
                            Routes.CONFIRMACION_RENOVACION
                        )
                    },

                    onVolver = {
                        navController.popBackStack()
                    }
                )

            } else {
                androidx.compose.material3.Text(
                    text = "Reserva no encontrada"
                )
            }
        }
        // Pantalla de confirmación de renovación
        composable(Routes.CONFIRMACION_RENOVACION) {

            val reservaActualizada = ReservasData.reservas.lastOrNull()

            if (reservaActualizada != null) {

                ConfirmacionRenovacionScreen(
                    reserva = reservaActualizada,
                    onVolverReservas = {
                        navController.navigate(Routes.MIS_RESERVAS) {
                            popUpTo(Routes.MIS_RESERVAS) {
                                inclusive = false
                            }
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}