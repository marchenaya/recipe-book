package com.marchenaya.core.model

data class Recipe(
    val id: Int,
    val title: String,
    val imageUrl: String?,
    val cookingTime: Int,
    val servings: Int,
    val ingredients: List<Ingredient>,
    val instructions: Map<String, List<Instruction>>
) {

    fun cookingTimeToHoursAndMinutes(hourAbbreviation: String, minuteAbbreviation: String): String {
        val hours = (cookingTime / 60)
        val minutes = cookingTime - hours * 60
        return if (hours == 0) {
            "$minutes $minuteAbbreviation"
        } else if (minutes == 0) {
            "$hours $hourAbbreviation"
        } else {
            "$hours $hourAbbreviation $minutes $minuteAbbreviation"
        }
    }

}