package com.marchenaya.data.remote.model.ingredient

import com.google.gson.annotations.SerializedName

data class IngredientRemote(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("measures") val measures: IngredientMeasureRemote
)
