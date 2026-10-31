package com.sena.navigationcompose.data

import com.sena.navigationcompose.model.Libro
import com.sena.navigationcompose.model.Reserva
import java.time.LocalDate

object ReservasData {

    val reservas = mutableListOf<Reserva>()

    private var siguienteId = 1

    fun agregarReserva(libro: Libro) {

        // Evitar reservar dos veces el mismo libro
        val existeReserva = reservas.any {
            it.libroId == libro.id && it.estado == "Activa"
        }

        if (!existeReserva) {

            val fechaActual = LocalDate.now()

            val reserva = Reserva(
                id = siguienteId++,
                libroId = libro.id,
                nombreLibro = libro.nombre,
                autor = libro.autor,
                fechaReserva = fechaActual.toString(),
                fechaDevolucion = fechaActual.plusDays(15).toString(),
                estado = "Activa"
            )

            reservas.add(reserva)
        }
    }

    fun obtenerReservas(): List<Reserva> {
        return reservas.toList()
    }

    fun obtenerReserva(id: Int): Reserva? {
        return reservas.find { it.id == id }
    }

    fun cancelarReserva(id: Int) {
        val posicion = reservas.indexOfFirst { it.id == id }

        if (posicion != -1) {
            reservas[posicion] = reservas[posicion].copy(
                estado = "Cancelada"
            )
        }
    }
}
