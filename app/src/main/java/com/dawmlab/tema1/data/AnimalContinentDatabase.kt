package com.dawmlab.tema1.data

import android.content.Context
import android.provider.Settings.Global
import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dawmlab.tema1.data.dao.AnimalContinentDao
import com.dawmlab.tema1.data.dao.AnimalDao
import com.dawmlab.tema1.data.dao.ContinentDao
import com.dawmlab.tema1.data.models.Animal
import com.dawmlab.tema1.data.models.AnimalContinentCrossRef
import com.dawmlab.tema1.data.models.Continent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

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


    fun destroyInstance() {
        INSTANCE = null
    }

    fun getAllAnimalsWIthContinents(): LiveData<List<AnimalContinentCrossRef>> {
        return animalContinentDao().getAllAnimalContinentCrossRef()

    }
    fun insertAnimal(animal: Animal) {
        GlobalScope.launch(Dispatchers.IO) {
            animalDao().insertAnimal(animal)
        }
    }
    fun insertContinent(continent: Continent) {
        GlobalScope.launch(Dispatchers.IO) {
            continentDao().insertContinent(continent)
        }
    }
    fun insertAnimalContinentCrossRef(animalContinentCrossRef: AnimalContinentCrossRef) {
        GlobalScope.launch(Dispatchers.IO) {
            animalContinentDao().insertAnimalContinentCrossRef(animalContinentCrossRef)
        }
    }
    suspend fun getAnimalIdByName(name: String): Int {
        return withContext(Dispatchers.IO) {
            animalDao().getAnimalIdByName(name)
        }
    }

    suspend fun getContinentIdByName(name: String): Int {
        return withContext(Dispatchers.IO) {
            continentDao().getContinentIdByName(name)
        }
    }

    fun getAnimalNameById(id: Int): String {
        var animalName = ""
        runBlocking {
            launch(Dispatchers.IO) {
                animalName = animalDao().getAnimalNameById(id)
            }
        }
        return animalName
    }

    fun getContinentNameById(id: Int): String {
        var continentName = ""
        runBlocking {
            launch(Dispatchers.IO) {
                continentName = continentDao().getContinentNameById(id)
            }
        }
        return continentName
    }

    fun updateAnimalContinentCrossRef(animalId: Int, continentId: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            animalContinentDao().updateAnimalContinentCrossRef(animalId, continentId)
        }
    }

    fun deleteAnimal(animal: Animal) {
        GlobalScope.launch(Dispatchers.IO) {
            animalDao().deleteAnimal(animal)
        }
    }
    fun deleteAnimalContinentCrossRef(animalId: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            animalContinentDao().deleteAnimalContinentCrossRef(animalId)
        }
    }



}