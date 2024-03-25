package com.marchenaya.data.database.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.marchenaya.data.database.model.IngredientEntity
import com.marchenaya.data.database.model.InstructionEntity
import com.marchenaya.data.database.model.RecipeEntity

data class RecipeWithIngredientsAndInstructions(
    @Embedded
    val recipeEntity: RecipeEntity,
    @Relation(parentColumn = "id", entityColumn = "recipe_id")
    val ingredientEntityList: List<IngredientEntity>,
    @Relation(parentColumn = "id", entityColumn = "recipe_id")
    val instructionEntityList: List<InstructionEntity>
)