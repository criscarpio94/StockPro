package com.example.stockpro

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class StockViewModel : ViewModel() {
    var productos = mutableStateListOf(
        //Lista de productos
        Producto(1, "Cemento", "Saco 50kg Guapan", 8.0, 10),
        Producto(2, "Carretilla", "Eterna verde", 50.0, 3),
        Producto(3, "Pintura Negra", "Esmalte Litro", 6.0, 7),
        Producto(4, "Brocha #4", "Wilson cerda fina", 3.5,  0),
        Producto(5, "Varilla corrugada", "Grosor 12mm", 14.0, 2),
        Producto(6, "Serrucho", "Bellota 12 pul", 7.0, 12)
    )

    fun obtenerProducto(id: Int): Producto? {
        return productos.find { it.id == id }
    }

    fun actualizarStock(id: Int, nuevaCantidad: Int) {
        val producto = obtenerProducto(id)
        producto?.stockActual = nuevaCantidad
    }

    fun calcularValorTotalInventario(): Double {
        return productos.sumOf { it.precio * it.stockActual }
    }

    fun obtenerProductosRiesgo(): List<Producto> {
        return productos.filter { it.stockActual < 5 }
    }

    fun obtenerProductosCero(): Int {
        return productos.count { it.stockActual == 0 }
    }
}
