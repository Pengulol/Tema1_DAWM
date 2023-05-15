package com.dawmlab.tema1.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "continent")
data class Continent(
    val name: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
)
