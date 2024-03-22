package com.marchenaya.data.remote.datasource

import com.marchenaya.data.dispatcher.Dispatcher
import com.marchenaya.data.dispatcher.Dispatchers
import com.marchenaya.data.exception.HttpClientException
import com.marchenaya.data.exception.NotFoundException
import com.marchenaya.data.exception.OverQuotaException
import com.marchenaya.data.exception.RequestFailException
import com.marchenaya.data.exception.ServerException
import com.marchenaya.data.remote.api.RecipesApi
import com.marchenaya.data.remote.model.recipe.RecipeRemote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class RecipeRemoteDataSourceImpl @Inject constructor(
    @Dispatcher(Dispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val api: RecipesApi
) : RecipeRemoteDataSource {

    override suspend fun getRandomRecipes(): List<RecipeRemote> =
        withContext(ioDispatcher) {
            val response = api.getRandomRecipes()
            checkCode(response)
            response.body()?.recipeRemoteList ?: emptyList()
        }

    override suspend fun getRecipeById(id: Int): RecipeRemote? =
        withContext(ioDispatcher) {
            val response = api.getRecipeById(id)
            checkCode(response)
            response.body()
        }

    private fun <T> checkCode(response: Response<T>) {
        if (!response.isSuccessful) {
            when (response.code()) {
                402 -> throw OverQuotaException()
                404 -> throw NotFoundException()
                in 400..499 -> throw HttpClientException()
                in 500..599 -> throw ServerException()
                else -> throw RequestFailException()
            }
        }
    }

}