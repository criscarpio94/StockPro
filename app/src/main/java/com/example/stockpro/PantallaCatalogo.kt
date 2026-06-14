package com.example.stockpro

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun PantallaCatalogo(controlNavegacion: NavController, viewModel: StockViewModel, nombre: String) {
    var mostrarCriticos by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            "Operario: $nombre",
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        //Botones
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = { mostrarCriticos = false }) {
                Text("Ver Todo")
            }
            Button(onClick = { mostrarCriticos = true }) {
                Text("Stock Criticos")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        //listado de productos
        val productos = if (mostrarCriticos) {
            viewModel.obtenerProductosRiesgo()
        } else {
            viewModel.productos
        }

        LazyColumn {
            items(productos) { producto ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    onClick = { controlNavegacion.navigate("edicion/$producto.id")
                    }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(producto.nombre, fontWeight = FontWeight.Bold)
                        Text("Precio: $${producto.precio}")
                        Text("Stock: $${producto.stockActual}",
                        color = if (producto.stockActual < 5) Color.Red else Color.Black
                        )

                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        //boton inferior
        Button(
            onClick = { controlNavegacion.navigate("reporte") },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Ver reporte Financiero")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPantallaCatalogo() {
    val controladorNavegacion = rememberNavController()
    val viewModel = StockViewModel()
    PantallaCatalogo(controladorNavegacion, viewModel, "Christopher")
}
