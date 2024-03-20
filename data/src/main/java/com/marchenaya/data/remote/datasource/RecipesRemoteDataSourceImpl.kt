package com.marchenaya.data.remote.datasource

import com.marchenaya.data.Dispatcher
import com.marchenaya.data.Dispatchers
import com.marchenaya.data.remote.api.RecipesApi
import com.marchenaya.data.remote.model.recipe.RecipeRemote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipesRemoteDataSourceImpl @Inject constructor(
    @Dispatcher(Dispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val api: RecipesApi
) : RecipesRemoteDataSource {

    override suspend fun getRandomRecipes(): List<RecipeRemote> =
        withContext(ioDispatcher) {
            //todo : uncomment
            api.getRandomRecipes().body()?.recipes ?: emptyList()
            //todo : remove
//            MutableList(10) { index ->
//                RecipeRemote(
//                    index,
//                    "Elephant $index",
//                    "https://spoonacular.com/recipeImages/716430-556x370.jpg", 160,
//                    2,
//                    listOf(
//                        IngredientRemote(
//                            1, "Cheese", IngredientMeasureRemote(
//                                IngredientMetricMeasureRemote(100.0, "g")
//                            )
//                        )
//                    )
//                )
//            }
        }

    override suspend fun getRecipeById(id: Int): RecipeRemote? =
        withContext(ioDispatcher) {
            api.getRecipeById(id).body()
        }

}