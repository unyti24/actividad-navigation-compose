package com.sena.navigationcompose.ui.screens.libros

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Android
import androidx.compose.material.icons.outlined.Architecture
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sena.navigationcompose.model.Libro

private data class LibroVisual(
    val libro: Libro,
    val icono: ImageVector,
    val color: Color
)

@Composable
fun ListaLibrosScreen(
    onVerDetalle: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val libros = listOf(
        LibroVisual(
            libro = Libro(
                id = 1,
                nombre = "Clean Code",
                autor = "Robert C. Martin",
                categoria = "Programación",
                descripcion = "Una guía para escribir código limpio, organizado y fácil de mantener."
            ),
            icono = Icons.Outlined.Code,
            color = Color(0xFF29256B)
        ),
        LibroVisual(
            libro = Libro(
                id = 2,
                nombre = "Kotlin para Android",
                autor = "Desarrollo Android",
                categoria = "Desarrollo móvil",
                descripcion = "Conceptos y herramientas para desarrollar aplicaciones Android con Kotlin."
            ),
            icono = Icons.Outlined.Android,
            color = Color(0xFF43A047)
        ),
        LibroVisual(
            libro = Libro(
                id = 3,
                nombre = "Arquitectura de Software",
                autor = "Autor de referencia",
                categoria = "Arquitectura",
                descripcion = "Principios para organizar y diseñar aplicaciones de software."
            ),
            icono = Icons.Outlined.Architecture,
            color = Color(0xFFEF8B16)
        ),
        LibroVisual(
            libro = Libro(
                id = 4,
                nombre = "Jetpack Compose",
                autor = "Desarrollo Android",
                categoria = "Interfaces móviles",
                descripcion = "Introducción al desarrollo de interfaces modernas con Jetpack Compose."
            ),
            icono = Icons.Outlined.MenuBook,
            color = Color(0xFF00A5B5)
        )
    )


    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Mis Libros",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Elige un libro para ver más detalles.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(libros) { libroVisual ->

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 3.dp
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(64.dp)
                                    .background(
                                        color = libroVisual.color.copy(alpha = 0.12f),
                                        shape = RoundedCornerShape(12.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = libroVisual.icono,
                                    contentDescription = "Icono de ${libroVisual.libro.nombre}",
                                    tint = libroVisual.color,
                                    modifier = Modifier.size(38.dp)
                                )
                            }

                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 12.dp)
                            ) {
                                Text(
                                    text = libroVisual.libro.nombre,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = libroVisual.libro.autor,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = libroVisual.libro.categoria,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = libroVisual.color
                                )
                            }

                            Button(
                                onClick = {
                                    onVerDetalle(libroVisual.libro.id)
                                }
                            ) {
                                Text(text = "Ver detalle")
                            }
                        }
                    }
                }
            }
        }

    }

}
