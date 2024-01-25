package com.marchenaya.recipe

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun InstructionsScreen(modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Hello InstructionsScreen!",
            modifier = modifier
        )
    }
}

@Preview
@Composable
fun InstructionsScreenPreview() {
    InstructionsScreen()
}