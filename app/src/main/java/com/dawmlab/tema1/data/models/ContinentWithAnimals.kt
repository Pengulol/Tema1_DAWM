package com.dawmlab.tema1.data.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ContinentWithAnimals(
    @Embedded val continent: Continent,
    @Relation(
        parentColumn = "continentId",
        entityColumn = "animalId",
        associateBy = Junction(AnimalContinentCrossRef::class)
    )
    val playlists: List<Animal>
)