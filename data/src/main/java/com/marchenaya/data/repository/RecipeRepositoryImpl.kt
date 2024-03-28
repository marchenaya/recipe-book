package com.marchenaya.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.marchenaya.core.model.RecipeModel
import com.marchenaya.data.database.datasource.RecipeLocalDataSource
import com.marchenaya.data.dispatcher.Dispatcher
import com.marchenaya.data.dispatcher.Dispatchers
import com.marchenaya.data.exception.OverQuotaException
import com.marchenaya.data.exception.ServerException
import com.marchenaya.data.mapper.database.RecipeEntityDataMapper
import com.marchenaya.data.mapper.remote.RecipeRemoteDataMapper
import com.marchenaya.data.remote.datasource.RecipeRemoteDataSource
import com.marchenaya.data.remote.network.NetworkManager
import com.marchenaya.data.remote.pagingsource.RandomRecipePagingSource
import com.marchenaya.domain.repository.RecipeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val randomRecipePagingSource: RandomRecipePagingSource,
    private val remoteDataSource: RecipeRemoteDataSource,
    private val localDataSource: RecipeLocalDataSource,
    private val recipeRemoteDataMapper: RecipeRemoteDataMapper,
    private val recipeEntityDataMapper: RecipeEntityDataMapper,
    private val networkManager: NetworkManager,
    @Dispatcher(Dispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : RecipeRepository {

    override fun getRandomRecipes(): Flow<PagingData<RecipeModel>> {
        return Pager(
            config = PagingConfig(NETWORK_PAGE_SIZE),
            pagingSourceFactory = { randomRecipePagingSource }
        ).flow.cachedIn(CoroutineScope(ioDispatcher))
    }

    override suspend fun getRecipeById(id: Int): RecipeModel? {
        return if (networkManager.isNetworkAvailable()) {
            try {
                val recipe = remoteDataSource.getRecipeById(id)
                if (recipe != null) {
                    val recipeModel = recipeRemoteDataMapper.transformRemoteToModel(recipe)
                    val recipeEntity = recipeEntityDataMapper.transformModelToEntity(recipeModel)
                    localDataSource.saveRecipe(
                        recipeEntity.recipeEntity,
                        recipeEntity.ingredientEntityList,
                        recipeEntity.instructionEntityList
                    )
                    return recipeModel
                } else {
                    getLocalRecipeById(id)
                }
            } catch (exception: OverQuotaException) {
                getLocalRecipeById(id)
            } catch (exception: ServerException) {
                getLocalRecipeById(id)
            }
        } else {
            getLocalRecipeById(id)
        }
    }

    private suspend fun getLocalRecipeById(id: Int): RecipeModel? =
        localDataSource.getRecipeById(id)?.let { recipeEntity ->
            recipeEntityDataMapper.transformEntityToModel(recipeEntity)
        }

    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }

}