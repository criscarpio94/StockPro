package com.example.stockpro

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.NavController

@Composable
fun AppNavegacion(viewModel: StockViewModel) {
    val controladorNavegacion = rememberNavController()

    NavHost(navController = controladorNavegacion, startDestination = "ingreso") {
        composable("ingreso") {
            PantallaIngreso(controladorNavegacion)
        }
        composable("catalogo/{nombre}") { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre")
            PantallaCatalogo(controladorNavegacion, viewModel, nombre)
        }
        composable("edicion/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
            PantallaEdicion(controladorNavegacion, viewModel, id)
        }
        composable("reporte") {
            PantallaReporte(controladorNavegacion, viewModel)
        }
    }
}