package com.marchenaya.data.remote.model

import com.google.gson.annotations.SerializedName

data class IngredientMeasureRemote(
    @SerializedName("metric") val metricMeasure: IngredientMetricMeasureRemote
)
