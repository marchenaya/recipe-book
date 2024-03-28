package com.marchenaya.core.model

data class RecipeModel(
    val id: Int,
    val title: String,
    val imageUrl: String?,
    val cookingTime: Int,
    val servings: Int,
    val ingredientModels: List<IngredientModel>,
    val instructions: Map<String, List<InstructionModel>>
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

    companion object {
        val recipePreview = RecipeModel(
            id = 1,
            title = "Recipe title",
            imageUrl = "https://www.referenseo.com/wp-content/uploads/2019/03/image-attractive-960x540.jpg",
            cookingTime = 160,
            servings = 2,
            ingredientModels = listOf(
                IngredientModel(
                    1,
                    "Cheese",
                    "100.0 g"
                )
            ),
            instructions = mapOf(
                "" to listOf(
                    InstructionModel(
                        1,
                        "Cook"
                    )
                )
            )
        )
    }

}