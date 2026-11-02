package com.sena.navigationcompose.ui.screens.reservas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sena.navigationcompose.data.ReservasData
import com.sena.navigationcompose.model.Reserva
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MisReservasScreen(
    onVerDetalle: (Int) -> Unit
) {

    val reservas = ReservasData.obtenerReservas()
        .filter { it.estado == "Activa" }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Mis reservas")
                }
            )
        }
    ) { padding ->

        if (reservas.isEmpty()) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Icon(
                    imageVector = Icons.Default.Book,
                    contentDescription = null,
                    modifier = Modifier.size(65.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Todavía no tienes reservas",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Cuando reserves un libro aparecerá aquí."
                )
            }

        } else {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(reservas) { reserva ->

                    ReservaItem(
                        reserva = reserva,
                        onClick = {
                            onVerDetalle(reserva.id)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ReservaItem(
    reserva: Reserva,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Default.Book,
                contentDescription = "Libro",
                modifier = Modifier.size(45.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = reserva.nombreLibro,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = reserva.autor,
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "Estado: ${reserva.estado}",
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
