package com.sena.navigationcompose.model

data class Reserva(
    val id: Int,
    val libroId: Int,
    val nombreLibro: String,
    val autor: String,
    val fechaReserva: String,
    val fechaDevolucion: String,
    val estado: String
)
