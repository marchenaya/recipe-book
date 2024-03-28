package com.marchenaya.core.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.marchenaya.core.ui.theme.RecipeBookTheme
import com.marchenaya.ui.R

@Composable
fun RecipeBookText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Text(
        text = text,
        style = style,
        color = color,
        modifier = modifier
    )
}

@Preview
@Composable
fun RecipeBookTextPreview() {
    RecipeBookTheme {
        RecipeBookText(
            text = stringResource(R.string.recipe_title_preview),
            style = MaterialTheme.typography.titleLarge
        )
    }
}