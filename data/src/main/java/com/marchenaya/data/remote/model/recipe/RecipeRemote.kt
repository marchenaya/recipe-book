package com.marchenaya.data.remote.model.recipe

import com.google.gson.annotations.SerializedName
import com.marchenaya.data.remote.model.ingredient.IngredientRemote
import com.marchenaya.data.remote.model.instruction.InstructionRemote

data class RecipeRemote(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("image") val imageUrl: String?,
    @SerializedName("readyInMinutes") val cookingTime: Int,
    @SerializedName("servings") val servings: Int,
    @SerializedName("extendedIngredients") val ingredients: List<IngredientRemote>,
    @SerializedName("analyzedInstructions") val instructions: List<InstructionRemote>
)