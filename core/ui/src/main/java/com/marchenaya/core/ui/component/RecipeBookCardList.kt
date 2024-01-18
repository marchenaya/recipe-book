package com.marchenaya.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.marchenaya.core.model.Recipe
import com.marchenaya.core.ui.theme.RecipeBookTheme
import com.marchenaya.ui.R
import kotlinx.coroutines.flow.flowOf

@Composable
fun RecipeBookCardList(
    items: LazyPagingItems<Recipe>,
    onCardClick: (Int) -> Unit
) {
    when (items.loadState.refresh) {
        LoadState.Loading -> {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }

        is LoadState.Error -> {
            RecipeBookText(
                text = stringResource(R.string.error),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(all = 16.dp)
            )
        }

        is LoadState.NotLoading -> {
            if (items.itemCount > 0) {
                LazyRow(
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(items.itemCount) { index ->
                        val cardId = items[index]?.id ?: 0
                        val cardImage = items[index]?.imageUrl ?: ""
                        val cardText = items[index]?.title ?: ""
                        RecipeBookCard(
                            cardImage = cardImage, cardText = cardText
                        ) {
                            onCardClick(cardId)
                        }
                    }
                }
            } else {
                RecipeBookText(
                    text = stringResource(R.string.empty_recipes),
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(all = 16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun RecipeBookCardListPreview() {
    RecipeBookTheme {
        RecipeBookCardList(
            flowOf(
                PagingData.from(
                    MutableList(10) {
                        Recipe(
                            1,
                            stringResource(id = R.string.card_text_preview),
                            stringResource(id = R.string.image_preview)
                        )
                    }
                )
            ).collectAsLazyPagingItems()) {}
    }
}