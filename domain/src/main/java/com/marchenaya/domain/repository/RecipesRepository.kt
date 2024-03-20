package com.marchenaya.domain.repository

import androidx.paging.PagingData
import com.marchenaya.core.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {

    fun getRandomRecipes(): Flow<PagingData<Recipe>>

    suspend fun getRecipeById(id: Int): Recipe?

}