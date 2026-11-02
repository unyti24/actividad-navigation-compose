package com.sena.navigationcompose.data

import com.sena.navigationcompose.R
import com.sena.navigationcompose.model.Libro

object LibrosData {

    val libros = listOf(

        Libro(
            id = 1,
            nombre = "Clean Code",
            autor = "Robert C. Martin",
            categoria = "Programación",
            descripcion = "Una guía para escribir código limpio, organizado y fácil de mantener.",
            imagen = R.drawable.clean_code
        ),

        Libro(
            id = 2,
            nombre = "Kotlin para Android",
            autor = "Desarrollo Android",
            categoria = "Desarrollo móvil",
            descripcion = "Conceptos y herramientas para desarrollar aplicaciones Android con Kotlin.",
            imagen = R.drawable.kotlin_android
        ),

        Libro(
            id = 3,
            nombre = "Arquitectura de Software",
            autor = "Autor de referencia",
            categoria = "Arquitectura",
            descripcion = "Principios para organizar y diseñar aplicaciones de software.",
            imagen = R.drawable.arquitectura
        ),

        Libro(
            id = 4,
            nombre = "Jetpack Compose",
            autor = "Desarrollo Android",
            categoria = "Interfaces móviles",
            descripcion = "Introducción al desarrollo de interfaces modernas con Jetpack Compose.",
            imagen = R.drawable.jetpack_compose
        )
    )
}
