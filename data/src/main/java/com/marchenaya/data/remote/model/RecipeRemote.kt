package com.marchenaya.data.remote.model

import com.google.gson.annotations.SerializedName

data class RecipeRemote(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("image") val imageUrl: String?
)