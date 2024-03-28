package com.marchenaya.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.marchenaya.data.database.model.InstructionEntity

@Dao
interface InstructionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInstructionList(entityList: List<InstructionEntity>)

}