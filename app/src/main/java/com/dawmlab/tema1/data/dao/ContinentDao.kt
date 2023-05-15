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
    @Query("SELECT * FROM continent WHERE id = :id")
    suspend fun getContinentById(id: Int): Continent

    //get continent by name
    @Transaction
    @Query("SELECT * FROM continent WHERE name = :name")
    suspend fun getContinentByName(name: String): Continent

    //get continent count
    @Transaction
    @Query("SELECT COUNT(*) FROM continent")
    suspend fun getContinentCount(): Int


}