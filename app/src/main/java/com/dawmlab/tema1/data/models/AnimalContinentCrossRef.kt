package com.dawmlab.tema1.data.models

import androidx.room.Entity


@Entity(tableName = "animal_continent" , primaryKeys = ["animalId", "continentId"])
data class AnimalContinentCrossRef(
    val animalId: Int,
    val continentId: Int
)



