package com.marchenaya.data.remote.datasource

import com.marchenaya.data.remote.model.RecipeRemote

interface RecipesRemoteDataSource {

    suspend fun getRandomRecipes(): List<RecipeRemote>

}