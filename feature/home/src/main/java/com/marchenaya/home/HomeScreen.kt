package com.marchenaya.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Text(
        text = "Hello HomeScreen!",
        modifier = modifier
    )
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}