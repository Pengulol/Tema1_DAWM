package com.dawmlab.tema1.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animal")
data class Animal(
    val name: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
)
