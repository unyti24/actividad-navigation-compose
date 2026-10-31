package com.sena.navigationcompose.navigation

object Routes {
    fun detalleLibro(libroId: Int): String {
        return "detalle_libro/$libroId"
    }

    const val HOME = "home"

    const val LISTA_LIBROS = "lista_libros"

    const val DETALLE_LIBRO = "detalle_libro/{libroId}"

    const val CONFIRMACION_RESERVA = "confirmacion_reserva"

    const val MIS_RESERVAS = "mis_reservas"

    const val DETALLE_RESERVA = "detalle_reserva/{reservaId}"

    const val RENOVAR_RESERVA = "renovar_reserva"

    const val CONFIRMACION_RENOVACION = "confirmacion_renovacion"
}
