package com.marchenaya.recipe

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RecipeScreen(
    onBackClick: () -> Unit,
    onInstructionsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        IconButton(onClick = { onBackClick() }) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = stringResource(R.string.back),
            )
        }
        Text(
            text = "Hello RecipeScreen!",
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
    RecipeScreen(onBackClick = { }, onInstructionsClick = { })
}