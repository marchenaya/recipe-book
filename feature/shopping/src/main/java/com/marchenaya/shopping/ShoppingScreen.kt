package com.marchenaya.shopping

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ShoppingScreen(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.shopping_screen_preview),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun ShoppingScreenPreview() {
    ShoppingScreen()
}