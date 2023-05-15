package com.dawmlab.tema1.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.dawmlab.tema1.data.models.Continent

@Dao
interface ContinentDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertContinent(continent: Continent)

    @Delete
    suspend fun deleteContinent(continent: Continent)

    @Update
    suspend fun updateContinent(continent: Continent)

    //get continent by id

    @Transaction
    @Query("SELECT name FROM continent WHERE id = :id")
    suspend fun getContinentNameById(id: Int): String

    //get continent by name
    @Transaction
    @Query("SELECT id FROM continent WHERE name = :name")
    suspend fun getContinentIdByName(name: String): Int

    //get continent count
    @Transaction
    @Query("SELECT COUNT(*) FROM continent")
    suspend fun getContinentCount(): Int


}