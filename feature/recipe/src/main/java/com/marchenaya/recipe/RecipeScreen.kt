package com.marchenaya.recipe

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RecipeScreen(
    recipeId: Int?,
    onInstructionsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = "Hello RecipeScreen with id : $recipeId !",
            modifier = modifier
        )
        Button(onClick = { onInstructionsClick() }) {
            Text(text = "Navigate to Instructions Screen")
        }
    }
}

@Preview
@Composable
fun RecipeScreenPreview() {
    RecipeScreen(
        onInstructionsClick = { },
        recipeId = 0
    )
}