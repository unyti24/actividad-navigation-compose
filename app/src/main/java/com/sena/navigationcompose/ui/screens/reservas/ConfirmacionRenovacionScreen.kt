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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sena.navigationcompose.model.Reserva

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmacionRenovacionScreen(
    reserva: Reserva,
    onVolverReservas: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Renovación exitosa")
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "¡Reserva renovada! 🎉",
                fontSize = 26.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

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
                        fontSize = 21.sp
                    )

                    Text("Autor: ${reserva.autor}")

                    Text(
                        text = "Nueva fecha de devolución:"
                    )

                    Text(
                        text = reserva.fechaDevolucion,
                        fontSize = 20.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            Button(
                onClick = onVolverReservas,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver a mis reservas")
            }
        }
    }
}