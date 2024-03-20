package com.marchenaya.data.remote.datasource

import com.marchenaya.data.Dispatcher
import com.marchenaya.data.Dispatchers
import com.marchenaya.data.remote.api.RecipesApi
import com.marchenaya.data.remote.model.RecipeRemote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipesRemoteDataSourceImpl @Inject constructor(
    @Dispatcher(Dispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val api: RecipesApi
) : RecipesRemoteDataSource {

    override suspend fun getRandomRecipes(): List<RecipeRemote> =
        withContext(ioDispatcher) {
            api.getRandomRecipes().body()?.recipes ?: emptyList()
        }

    override suspend fun getRecipeById(id: Int): RecipeRemote? =
        withContext(ioDispatcher) {
            api.getRecipeById(id).body()
        }

}