package com.marchenaya.data.di

import com.marchenaya.data.remote.datasource.RecipesRemoteDataSource
import com.marchenaya.data.remote.datasource.RecipesRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindRecipesRemoteDataSource(
        impl: RecipesRemoteDataSourceImpl
    ): RecipesRemoteDataSource

}