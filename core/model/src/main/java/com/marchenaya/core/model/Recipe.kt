package com.marchenaya.core.model

data class Recipe(
    val id: Int,
    val title: String,
    val imageUrl: String?,
    val cookingTime: Int,
    val servings: Int
) {

    fun cookingTimeToHoursAndMinutes(): String {
        val hours = (cookingTime / 60)
        val minutes = cookingTime - hours * 60
        return if (hours == 0) {
            "$minutes min"
        } else if (minutes == 0) {
            "$hours h"
        } else {
            "$hours h $minutes min"
        }
    }

}