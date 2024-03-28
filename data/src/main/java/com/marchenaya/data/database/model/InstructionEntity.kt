package com.marchenaya.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "instruction", primaryKeys = ["id", "recipe_id"])
data class InstructionEntity(
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("recipe_id")
    val recipeId: Int,
    @ColumnInfo("instruction")
    val instruction: String,
    @ColumnInfo("instruction_name")
    val instructionName: String
)