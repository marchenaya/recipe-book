package com.marchenaya.data.database.datasource

import com.marchenaya.data.database.model.IngredientEntity
import com.marchenaya.data.database.model.InstructionEntity
import com.marchenaya.data.database.model.RecipeEntity
import com.marchenaya.data.database.relation.RecipeWithIngredientsAndInstructions

interface RecipeLocalDataSource {

    suspend fun saveRecipe(
        recipeEntity: RecipeEntity,
        ingredientEntityList: List<IngredientEntity>,
        instructionEntityList: List<InstructionEntity>
    ): RecipeWithIngredientsAndInstructions?

    suspend fun getRecipeList(): List<RecipeWithIngredientsAndInstructions>

    suspend fun getRecipeById(id: Int): RecipeWithIngredientsAndInstructions?

}