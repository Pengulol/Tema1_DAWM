package com.dawmlab.tema1.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.dawmlab.tema1.data.models.AnimalContinentCrossRef

@Dao
interface AnimalContinentDao {
    @Insert
    suspend fun insertAnimalContinentCrossRef(animalContinentCrossRef: AnimalContinentCrossRef)

    //get all

    @Transaction
    @Query("SELECT * FROM animal_continent")
    fun getAllAnimalContinentCrossRef(): LiveData<List<AnimalContinentCrossRef>>

    //update in animalContinentCrossRef by id of animal the continentId
    @Transaction
    @Query("UPDATE animal_continent SET continentId = :continentId WHERE animalId = :animalId")
    suspend fun updateAnimalContinentCrossRef(animalId: Int, continentId: Int)

    //delete in animalContinentCrossRef by id of animal
    @Transaction
    @Query("DELETE FROM animal_continent WHERE animalId = :animalId")
    suspend fun deleteAnimalContinentCrossRef(animalId: Int)
}