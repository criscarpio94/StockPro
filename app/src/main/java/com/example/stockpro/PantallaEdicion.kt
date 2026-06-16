package com.example.stockpro

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun PantallaEdicion(controladorNavegacion: NavController, viewModel: StockViewModel, id: Int) {
    val producto = viewModel.obtenerProducto(id)

    if (producto != null) {
        var stock by remember { mutableStateOf(producto.stockActual) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xC4C5C02C))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = producto.nombre,
                fontSize = 26.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = producto.descripcion,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Stock actual: $stock",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
            ) {
                Button(
                    onClick = { stock++ },
                    modifier = Modifier.width(120.dp),
                    shape = RoundedCornerShape(12.dp)

                ) {
                    Text("Sumar +1")
                }
                Button(
                    onClick = { if (stock > 0) stock -- },
                    enabled = stock > 0,
                    modifier = Modifier.width(120.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Restar -1")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                viewModel.actualizarStock(id, stock)
                controladorNavegacion.popBackStack()
                },
                modifier = Modifier
                    .width(180.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp)


                ) {
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
