package com.marchenaya.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.marchenaya.core.ui.theme.RecipeBookTheme

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    Column(verticalArrangement = Arrangement.Center, modifier = modifier.fillMaxSize()) {
        Text(
            text = stringResource(R.string.settings_screen_preview),
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    RecipeBookTheme {
        SettingsScreen()
    }
}
