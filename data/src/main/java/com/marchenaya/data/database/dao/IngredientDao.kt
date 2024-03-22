package com.marchenaya.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.marchenaya.data.database.model.IngredientEntity

@Dao
interface IngredientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIngredientList(entityList: List<IngredientEntity>)

}