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
            //todo : uncomment
            //api.getRandomRecipes().body()?.recipes ?: emptyList()
            //todo : remove
            MutableList(15) { index ->
                RecipeRemote(
                    index,
                    "Elephant $index",
                    "https://www.referenseo.com/wp-content/uploads/2019/03/image-attractive-960x540.jpg"
                )
            }
        }

}