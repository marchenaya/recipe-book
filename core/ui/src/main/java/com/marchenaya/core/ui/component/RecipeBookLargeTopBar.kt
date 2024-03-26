package com.marchenaya.core.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.marchenaya.ui.R

private const val ImageBaseHeight = 200
private const val TopBarBaseHeight = 60

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeBookLargeTopBar(
    onNavigationClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    title: String,
    imageUrl: String
) {
    val imageHeight = (1 - scrollBehavior.state.collapsedFraction) * ImageBaseHeight
    val topBarHeight = (1 - scrollBehavior.state.collapsedFraction) * TopBarBaseHeight
    Box(modifier = Modifier.fillMaxWidth()) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .build()
        )

        if (painter.state is AsyncImagePainter.State.Error) {
            Image(
                painter = painterResource(id = R.drawable.ic_placeholder_24dp),
                contentDescription = stringResource(R.string.recipe_image_description, title),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight.dp),
            )
        } else {
            Image(
                contentScale = ContentScale.Crop,
                painter = painter,
                contentDescription = stringResource(R.string.recipe_image_description, title),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight.dp)
            )
        }
        LargeTopAppBar(
            title = {
                Text(
                    text = title,
                    fontWeight = FontWeight.ExtraBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(end = 8.dp)
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
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
            scrollBehavior = scrollBehavior,
            windowInsets = WindowInsets(0.dp, 0.dp, 0.dp, topBarHeight.dp),
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun RecipeBookLargeTopBarPreview() {
    RecipeBookLargeTopBar(
        {},
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(),
        stringResource(id = R.string.text_preview),
        stringResource(id = R.string.image_preview)
    )
}
