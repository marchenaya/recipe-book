package com.marchenaya.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = "Hello HomeScreen!",
            modifier = modifier
        )
        Button(onClick = { onSearchClick() }) {
            Text(text = "Navigate to Search")
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(onSearchClick = {})
}