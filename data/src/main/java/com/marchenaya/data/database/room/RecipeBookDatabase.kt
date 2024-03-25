package com.marchenaya.data.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.marchenaya.data.database.dao.IngredientDao
import com.marchenaya.data.database.dao.InstructionDao
import com.marchenaya.data.database.dao.RecipeDao
import com.marchenaya.data.database.model.IngredientEntity
import com.marchenaya.data.database.model.InstructionEntity
import com.marchenaya.data.database.model.RecipeEntity

@Database(
    entities = [
        RecipeEntity::class,
        IngredientEntity::class,
        InstructionEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class RecipeBookDatabase : RoomDatabase() {

    abstract val recipeDao: RecipeDao

    abstract val ingredientDao: IngredientDao

    abstract val instructionDao: InstructionDao

    internal companion object {
        const val DATABASE_NAME = "recipe-book.database"
    }

}