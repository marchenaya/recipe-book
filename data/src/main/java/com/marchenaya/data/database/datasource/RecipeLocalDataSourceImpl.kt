package com.marchenaya.data.database.datasource

import androidx.room.withTransaction
import com.marchenaya.data.dispatcher.Dispatcher
import com.marchenaya.data.dispatcher.Dispatchers
import com.marchenaya.data.database.model.IngredientEntity
import com.marchenaya.data.database.model.InstructionEntity
import com.marchenaya.data.database.model.RecipeEntity
import com.marchenaya.data.database.relation.RecipeWithIngredientsAndInstructions
import com.marchenaya.data.database.room.RecipeBookDatabase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeLocalDataSourceImpl @Inject constructor(
    private val database: RecipeBookDatabase,
    @Dispatcher(Dispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : RecipeLocalDataSource {

    override suspend fun saveRecipe(
        recipeEntity: RecipeEntity,
        ingredientEntityList: List<IngredientEntity>,
        instructionEntityList: List<InstructionEntity>
    ): RecipeWithIngredientsAndInstructions? {
        return withContext(ioDispatcher) {
            database.withTransaction {
                database.recipeDao.insertRecipe(recipeEntity)
                database.ingredientDao.insertIngredientList(
                    ingredientEntityList.map { ingredient ->
                        ingredient.copy(recipeId = recipeEntity.id)
                    }
                )
                database.instructionDao.insertInstructionList(
                    instructionEntityList.map { instruction ->
                        instruction.copy(recipeId = recipeEntity.id)
                    }
                )
                database.recipeDao.selectRecipeWithIngredientsAndInstructionsByRecipeId(
                    recipeEntity.id
                )
            }
        }
    }

    override suspend fun getRecipeList(): List<RecipeWithIngredientsAndInstructions> {
        return withContext(ioDispatcher) {
            database.withTransaction {
                database.recipeDao.getAllRecipes()
            }
        }
    }

    override suspend fun getRecipeById(id: Int): RecipeWithIngredientsAndInstructions? {
        return withContext(ioDispatcher) {
            database.withTransaction {
                database.recipeDao.selectRecipeWithIngredientsAndInstructionsByRecipeId(id)
            }
        }
    }

}