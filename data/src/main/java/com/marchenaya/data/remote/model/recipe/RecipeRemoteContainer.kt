package com.marchenaya.data.remote.model.recipe

import com.google.gson.annotations.SerializedName

data class RecipeRemoteContainer(
    @SerializedName("recipes") val recipes: List<RecipeRemote>
)