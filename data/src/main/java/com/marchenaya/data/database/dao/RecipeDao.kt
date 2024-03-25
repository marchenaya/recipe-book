package com.marchenaya.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.marchenaya.data.database.model.RecipeEntity
import com.marchenaya.data.database.relation.RecipeWithIngredientsAndInstructions

@Dao
interface RecipeDao {

    @Transaction
    @Query("SELECT * FROM recipe ORDER BY recipe.name")
    fun getAllRecipes(): List<RecipeWithIngredientsAndInstructions>

    @Transaction
    @Query("SELECT * FROM recipe WHERE id=:id")
    suspend fun selectRecipeWithIngredientsAndInstructionsByRecipeId(id: Int): RecipeWithIngredientsAndInstructions?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipe(entity: RecipeEntity)

}