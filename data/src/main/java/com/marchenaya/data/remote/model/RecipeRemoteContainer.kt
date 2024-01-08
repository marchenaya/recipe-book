package com.marchenaya.data.remote.model

import com.google.gson.annotations.SerializedName

data class RecipeRemoteContainer(
    @SerializedName("recipes") val recipes: List<RecipeRemote>
)