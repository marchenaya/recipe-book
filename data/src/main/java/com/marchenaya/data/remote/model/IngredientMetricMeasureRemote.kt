package com.marchenaya.data.remote.model

import com.google.gson.annotations.SerializedName

data class IngredientMetricMeasureRemote(
    @SerializedName("amount") val amount: Double,
    @SerializedName("unitShort") val unit: String
)