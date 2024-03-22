package com.marchenaya.data.remote.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.marchenaya.core.model.Recipe
import com.marchenaya.data.database.datasource.RecipeLocalDataSource
import com.marchenaya.data.database.relation.RecipeWithIngredientsAndInstructions
import com.marchenaya.data.dispatcher.Dispatcher
import com.marchenaya.data.dispatcher.Dispatchers
import com.marchenaya.data.exception.OverQuotaException
import com.marchenaya.data.exception.ServerException
import com.marchenaya.data.mapper.database.RecipeEntityDataMapper
import com.marchenaya.data.mapper.remote.RecipeRemoteDataMapper
import com.marchenaya.data.remote.datasource.RecipeRemoteDataSource
import com.marchenaya.data.remote.network.NetworkManager
import com.marchenaya.data.repository.RecipeRepositoryImpl.Companion.NETWORK_PAGE_SIZE
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RandomRecipePagingSource @Inject constructor(
    private val remoteDataSource: RecipeRemoteDataSource,
    private val localDataSource: RecipeLocalDataSource,
    private val recipeEntityDataMapper: RecipeEntityDataMapper,
    private val recipeRemoteDataMapper: RecipeRemoteDataMapper,
    private val networkManager: NetworkManager,
    @Dispatcher(Dispatchers.Default) private val defaultDispatcher: CoroutineDispatcher,
) : PagingSource<Int, Recipe>() {

    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            if (networkManager.isNetworkAvailable()) {
                val response = remoteDataSource.getRandomRecipes()
                val recipeModelList = recipeRemoteDataMapper.transformRemoteList(response)
                val recipeEntityList = recipeEntityDataMapper.transformModelList(recipeModelList)
                val nextKey = if (response.isEmpty()) {
                    null
                } else {
                    position + (params.loadSize / NETWORK_PAGE_SIZE)
                }
                saveRecipes(recipeEntityList)
                LoadResult.Page(
                    data = recipeModelList,
                    prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                    nextKey = nextKey
                )
            } else {
                loadLocalData(position)
            }
        } catch (exception: OverQuotaException) {
            loadLocalData(position)
        } catch (exception: ServerException) {
            loadLocalData(position)
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    private suspend fun saveRecipes(recipeEntityList: List<RecipeWithIngredientsAndInstructions>) {
        withContext(defaultDispatcher) {
            recipeEntityList.forEach { recipe ->
                localDataSource.saveRecipe(
                    recipe.recipeEntity,
                    recipe.ingredientEntityList,
                    recipe.instructionEntityList
                )
            }
        }
    }

    private suspend fun loadLocalData(position: Int): LoadResult.Page<Int, Recipe> =
        LoadResult.Page(
            data = recipeEntityDataMapper.transformEntityList(localDataSource.getRecipeList()),
            prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
            nextKey = null
        )

    companion object {
        const val STARTING_PAGE_INDEX = 1
    }

}