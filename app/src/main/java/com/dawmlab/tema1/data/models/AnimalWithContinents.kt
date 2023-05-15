package com.dawmlab.tema1.data.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class AnimalWithContinents(
    @Embedded val animal: Animal,
    @Relation(
        parentColumn = "animalId",
        entityColumn = "continentId",
        associateBy = Junction(AnimalContinentCrossRef::class)
    )
    val songs: List<Continent>
)