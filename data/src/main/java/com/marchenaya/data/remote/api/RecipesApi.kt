package com.marchenaya.data.remote.api

import com.marchenaya.data.remote.api.ApiConstants.RANDOM_RECIPES_PATH
import com.marchenaya.data.remote.model.RecipeRemoteContainer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesApi {

    @GET(RANDOM_RECIPES_PATH)
    suspend fun getRandomRecipes(
        @Query("number") number: Int = 10
    ): Response<RecipeRemoteContainer>

}