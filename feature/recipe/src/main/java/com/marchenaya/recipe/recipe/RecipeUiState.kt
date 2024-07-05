package com.marchenaya.recipe.recipe

import com.marchenaya.domain.model.RecipeModel

sealed interface RecipeUiState {

    data class Success(val recipeModel: RecipeModel) : RecipeUiState

    data object Loading : RecipeUiState

    data class Error(val exception: Exception) : RecipeUiState

}

