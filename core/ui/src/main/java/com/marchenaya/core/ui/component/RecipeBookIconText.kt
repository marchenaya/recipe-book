package com.marchenaya.core.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marchenaya.core.ui.theme.RecipeBookTheme
import com.marchenaya.ui.R

@Composable
fun RecipeBookIconText(
    text: String,
    icon: ImageVector,
    iconContentDescription: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Icon(
            imageVector = icon,
            contentDescription = iconContentDescription,
            tint = color
        )
        Text(
            text = text,
            style = style,
            color = color,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Preview
@Composable
fun RecipeBookIconTimePreview() {
    RecipeBookTheme {
        RecipeBookIconText(
            text = stringResource(id = R.string.text_preview),
            icon = Icons.Rounded.DateRange,
            iconContentDescription = stringResource(id = R.string.content_description_preview)
        )
    }
}