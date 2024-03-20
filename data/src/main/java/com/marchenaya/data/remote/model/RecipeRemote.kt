package com.marchenaya.data.remote.model

import com.google.gson.annotations.SerializedName

data class RecipeRemote(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("image") val imageUrl: String?,
    @SerializedName("readyInMinutes") val cookingTime: Int,
    @SerializedName("servings") val servings: Int,
    @SerializedName("extendedIngredients") val ingredients: List<IngredientRemote>
)