package com.sena.navigationcompose.ui.screens.reservas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sena.navigationcompose.model.Reserva

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleReservaScreen(
    reserva: Reserva,
    onRenovar: () -> Unit,
    onCancelar: () -> Unit,
    onVolver: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Detalle de reserva")
                },
                navigationIcon = {
                    TextButton(onClick = onVolver) {
                        Text("Volver")
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = "Información del libro",
                fontSize = 22.sp
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Text(
                        text = reserva.nombreLibro,
                        fontSize = 22.sp
                    )

                    Text("Autor: ${reserva.autor}")

                    Text("Fecha de reserva: ${reserva.fechaReserva}")

                    Text("Fecha de devolución: ${reserva.fechaDevolucion}")

                    Text("Estado: ${reserva.estado}")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onRenovar,
                modifier = Modifier.fillMaxWidth(),
                enabled = reserva.estado == "Activa"
            ) {
                Text("Renovar reserva")
            }

            Button(
                onClick = onCancelar,
                modifier = Modifier.fillMaxWidth(),
                enabled = reserva.estado == "Activa"
            ) {
                Text("Cancelar reserva")
            }
        }
    }
}
