package com.marchenaya.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "ingredient", primaryKeys = ["id", "recipe_id"])
data class IngredientEntity(
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("recipe_id")
    val recipeId: Int,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("amount")
    val amount: String
)
