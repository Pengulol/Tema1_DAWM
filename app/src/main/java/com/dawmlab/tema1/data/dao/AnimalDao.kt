package com.dawmlab.tema1.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.dawmlab.tema1.data.models.Animal

@Dao
interface AnimalDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAnimal(animal: Animal)

    @Delete
    suspend fun deleteAnimal(animal: Animal)

    //get name from animal by id
    @Transaction
    @Query("SELECT name FROM animal WHERE id = :id")
    suspend fun getAnimalNameById(id: Int): String

    //get animal by name
    @Transaction
    @Query("SELECT * FROM animal WHERE name = :name")
    suspend fun getAnimalByName(name: String): Animal





}