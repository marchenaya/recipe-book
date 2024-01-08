package com.marchenaya.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.marchenaya.data.Dispatcher
import com.marchenaya.data.Dispatchers
import com.marchenaya.data.remote.model.RecipeRemote
import com.marchenaya.data.remote.pagingsource.RandomRecipePagingSource
import com.marchenaya.domain.model.Recipe
import com.marchenaya.domain.repository.RecipesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    @Dispatcher(Dispatchers.Default) private val defaultDispatcher: CoroutineDispatcher,
    private val randomRecipePagingSource: RandomRecipePagingSource
) : RecipesRepository {

    override fun getRandomRecipes(): Flow<PagingData<Recipe>> {
        return Pager(
            config = PagingConfig(NETWORK_PAGE_SIZE),
            pagingSourceFactory = { randomRecipePagingSource }
        ).flow.map { pagingData ->
            pagingData.map { recipeRemote -> recipeRemote.toRecipeDomain() }
        }
    }

    private suspend fun RecipeRemote.toRecipeDomain(): Recipe =
        withContext(defaultDispatcher) {
            Recipe(id, title, imageUrl)
        }

    companion object {
        const val NETWORK_PAGE_SIZE = 50
    }

}