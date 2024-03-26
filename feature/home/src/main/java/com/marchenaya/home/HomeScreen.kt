package com.marchenaya.home

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.marchenaya.core.model.Ingredient
import com.marchenaya.core.model.Instruction
import com.marchenaya.core.model.Recipe
import com.marchenaya.core.ui.component.RecipeBookCardList
import com.marchenaya.core.ui.theme.RecipeBookTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun HomeScreen(
    onRecipeClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val randomRecipesPagingItems: LazyPagingItems<Recipe> =
        viewModel.randomRecipesUiState.collectAsLazyPagingItems()

    HomeContentScreen(
        randomRecipesPagingItems = randomRecipesPagingItems,
        onRecipeClick = onRecipeClick,
        modifier = modifier
    )

}

@Composable
fun HomeContentScreen(
    randomRecipesPagingItems: LazyPagingItems<Recipe>,
    onRecipeClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    RecipeBookCardList(
        items = randomRecipesPagingItems,
        onRecipeClick,
        modifier.padding(top = 8.dp)
    )
}

@Preview
@Composable
fun HomeContentScreenPreview() {
    RecipeBookTheme {
        HomeContentScreen(
            flowOf(
                PagingData.from(
                    MutableList(10) {
                        Recipe(
                            1,
                            stringResource(id = com.marchenaya.ui.R.string.card_text_preview),
                            stringResource(id = com.marchenaya.ui.R.string.image_preview),
                            160,
                            2,
                            listOf(
                                Ingredient(
                                    1, "Cheese", "100.0 g"
                                )
                            ),
                            mapOf("" to listOf(Instruction(1, "Cook")))
                        )
                    }
                )
            ).collectAsLazyPagingItems(), {})
    }
}