package com.example.examenuf1.util

sealed class Routes(val route: String) {
    object MainActivity : Routes("MainActivity")
    object Pantalla1 : Routes("Pantalla1")
    object Pantalla2 : Routes("Pantalla2")
    object Pantalla3 : Routes("Pantalla3")
}