package com.marchenaya.data.di

import android.content.Context
import androidx.room.Room
import com.marchenaya.data.database.room.RecipeBookDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideRecipeBookDatabase(
        @ApplicationContext context: Context
    ): RecipeBookDatabase {
        return Room.databaseBuilder(
            context,
            RecipeBookDatabase::class.java,
            RecipeBookDatabase.DATABASE_NAME
        ).build()
    }

}