package com.marchenaya.search

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun SearchScreen(
    onFilterClick: () -> Unit,
    onRecipeClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = "Hello SearchScreen!",
            modifier = modifier
        )
        Button(onClick = { onFilterClick() }) {
            Text(text = "Navigate to Filter Screen")
        }
        Button(onClick = { onRecipeClick(1) }) {
            Text(text = "Navigate to Recipe Screen")
        }
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen(onFilterClick = {}, onRecipeClick = {})
}