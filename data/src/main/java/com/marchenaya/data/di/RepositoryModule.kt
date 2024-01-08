package com.marchenaya.data.di

import com.marchenaya.data.repository.RecipesRepositoryImpl
import com.marchenaya.domain.repository.RecipesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTelepeageRecipesRepository(
        recipesRepositoryImpl: RecipesRepositoryImpl
    ): RecipesRepository

}