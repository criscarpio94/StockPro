package com.example.stockpro

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.stockpro.ui.theme.StockProTheme
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun StockProApp() {
    val viewModel: StockViewModel = viewModel()
    AppNavegacion(viewModel)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewStockProApp() {
    StockProTheme {
        StockProApp()
    }
}

