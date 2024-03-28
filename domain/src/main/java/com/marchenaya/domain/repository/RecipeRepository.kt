package com.marchenaya.domain.repository

import androidx.paging.PagingData
import com.marchenaya.core.model.RecipeModel
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    fun getRandomRecipes(): Flow<PagingData<RecipeModel>>

    suspend fun getRecipeById(id: Int): RecipeModel?

}