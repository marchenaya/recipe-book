package com.marchenaya.core.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.marchenaya.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeBookLargeTopBar(
    onNavigationClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    title: String,
    imageUrl: String
) {

    LargeTopAppBar(
        title = {
            Column {
                Text(
                    title
                )
                AsyncImage(
                    model = imageUrl,
                    contentDescription = ""
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        navigationIcon = {
            IconButton(onClick = {
                onNavigationClick()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Localized description",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun RecipeBookLargeTopBarPreview() {
    RecipeBookLargeTopBar(
        {},
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(),
        "", stringResource(id = R.string.image_preview)
    )
}
