package com.marchenaya.data.remote.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.marchenaya.data.remote.datasource.RecipesRemoteDataSource
import com.marchenaya.data.remote.model.recipe.RecipeRemote
import com.marchenaya.data.repository.RecipesRepositoryImpl.Companion.NETWORK_PAGE_SIZE
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RandomRecipePagingSource @Inject constructor(
    private val remoteDataSource: RecipesRemoteDataSource
) : PagingSource<Int, RecipeRemote>() {

    override fun getRefreshKey(state: PagingState<Int, RecipeRemote>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RecipeRemote> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = remoteDataSource.getRandomRecipes()
            val nextKey = if (response.isEmpty()) {
                null
            } else {
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = remoteDataSource.getRandomRecipes(),
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    companion object {
        const val STARTING_PAGE_INDEX = 1
    }

}