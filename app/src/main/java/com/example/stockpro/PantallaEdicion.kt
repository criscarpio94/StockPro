package com.example.stockpro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun PantallaEdicion(controladorNavegacion: NavController, viewModel: StockViewModel, id: Int) {
    val producto = viewModel.obtenerProducto(id)

    if (producto != null) {
        var stock by remember { mutableStateOf(producto.stockActual) }

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(producto.nombre, fontSize = MaterialTheme.typography.titleLarge.fontSize, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            Text(producto.descripcion)
            Spacer(modifier = Modifier.height(16.dp))

            Text("Stock actual: $stock", fontSize = MaterialTheme.typography.titleMedium.fontSize)

            Spacer(modifier = Modifier.height(24.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(onClick = { stock++ }) {
                    Text("Sumar +1")
                }
                Button(onClick = { if (stock > 0) stock -- }, enabled = stock > 0) {
                    Text("Restar -1")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = {
                viewModel.actualizarStock(id, stock)
                controladorNavegacion.popBackStack()
            }) {
                Text("Guardar y Volver")
            }
        }
    } else {
        Text("Produco no encontrado")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPantallaEdicion() {
    val controladorNavegacion = rememberNavController()
    val viewModel = StockViewModel()
    PantallaEdicion(controladorNavegacion, viewModel, 1)
}
