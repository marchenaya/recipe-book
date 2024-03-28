package com.marchenaya.data.di

import com.marchenaya.data.database.datasource.RecipeLocalDataSource
import com.marchenaya.data.database.datasource.RecipeLocalDataSourceImpl
import com.marchenaya.data.remote.datasource.RecipeRemoteDataSource
import com.marchenaya.data.remote.datasource.RecipeRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindRecipeRemoteDataSource(
        impl: RecipeRemoteDataSourceImpl
    ): RecipeRemoteDataSource

    @Binds
    abstract fun bindRecipeLocalDataSource(
        impl: RecipeLocalDataSourceImpl
    ): RecipeLocalDataSource

}