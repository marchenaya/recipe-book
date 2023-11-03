package com.marchenaya.recipe

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun InstructionsScreen(onBackClick: () -> Unit, modifier: Modifier = Modifier) {
    Column {
        IconButton(onClick = { onBackClick() }) {
            Icon(
                imageVector = Icons.Rounded.Close,
                contentDescription = stringResource(R.string.close),
            )
        }
        Text(
            text = "Hello InstructionsScreen!",
            modifier = modifier
        )
    }
}

@Preview
@Composable
fun InstructionsScreenPreview() {
    InstructionsScreen({ })
}