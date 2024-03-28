package com.marchenaya.data.remote.datasource

import com.marchenaya.data.remote.model.recipe.RecipeRemote

interface RecipeRemoteDataSource {

    suspend fun getRandomRecipes(): List<RecipeRemote>

    suspend fun getRecipeById(id: Int): RecipeRemote?

}