package com.dawmlab.tema1.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dawmlab.tema1.data.dao.AnimalContinentDao
import com.dawmlab.tema1.data.dao.AnimalDao
import com.dawmlab.tema1.data.dao.ContinentDao
import com.dawmlab.tema1.data.models.Animal
import com.dawmlab.tema1.data.models.AnimalContinentCrossRef
import com.dawmlab.tema1.data.models.Continent

@Database(
    entities = [Animal::class, Continent::class, AnimalContinentCrossRef::class],
    version = 1
)
abstract class AnimalContinentDatabase : RoomDatabase() {
    abstract fun animalDao(): AnimalDao
    abstract fun continentDao(): ContinentDao
    abstract fun animalContinentDao(): AnimalContinentDao

    companion object {
        @Volatile
        private var INSTANCE: AnimalContinentDatabase? = null

        fun getInstance(context: Context): AnimalContinentDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AnimalContinentDatabase::class.java,
                    "animal_continent_database"
                ).build().also {
                    INSTANCE = it
                }
            }


        }

    }
}