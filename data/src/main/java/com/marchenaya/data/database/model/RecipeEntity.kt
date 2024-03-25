package com.marchenaya.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class RecipeEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("image_url")
    val imageUrl: String?,
    @ColumnInfo("cooking_time")
    val cookingTime: Int,
    @ColumnInfo("servings")
    val servings: Int
)