package com.example.stockpro

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.stockpro.ui.theme.StockProTheme

@Composable
fun StockProApp() {
    val viewModel = StockViewModel()
    AppNavegacion(viewModel)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewStockProApp() {
    StockProTheme {
        StockProApp()
    }
}

