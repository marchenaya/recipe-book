package com.marchenaya.shopping

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ShoppingScreen(modifier: Modifier = Modifier) {
    Text(
        text = "Hello ShoppingScreen!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun ShoppingScreenPreview() {
    ShoppingScreen()
}