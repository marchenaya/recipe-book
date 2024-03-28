package com.marchenaya.home

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.marchenaya.core.model.RecipeModel
import com.marchenaya.core.ui.component.RecipeBookCardList
import com.marchenaya.core.ui.theme.RecipeBookTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun HomeScreen(
    onRecipeClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {

    var isRetrieveRandomRecipesCalled by rememberSaveable {
        mutableStateOf(false)
    }

    val randomRecipesPagingItems: LazyPagingItems<RecipeModel> =
        viewModel.randomRecipesUiState.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        if (!isRetrieveRandomRecipesCalled) {
            isRetrieveRandomRecipesCalled = true
            viewModel.retrieveRandomRecipes()
        }
    }

    HomeContentScreen(
        randomRecipesPagingItems = randomRecipesPagingItems,
        onRecipeClick = onRecipeClick,
        modifier = modifier
    )

}

@Composable
fun HomeContentScreen(
    randomRecipesPagingItems: LazyPagingItems<RecipeModel>,
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
                        RecipeModel.recipePreview
                    },
                    sourceLoadStates = LoadStates(
                        LoadState.NotLoading(false),
                        LoadState.NotLoading(false),
                        LoadState.NotLoading(false)
                    )
                )
            ).collectAsLazyPagingItems(), {})
    }
}