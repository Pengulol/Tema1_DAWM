package com.dawmlab.tema1.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dawmlab.tema1.adapters.MultiViewTypeAdapter
import com.dawmlab.tema1.OnItemClickListener
import com.dawmlab.tema1.R
import com.dawmlab.tema1.data.AnimalContinentDatabase
import com.dawmlab.tema1.data.dao.AnimalContinentDao
import com.dawmlab.tema1.data.dao.AnimalDao
import com.dawmlab.tema1.data.dao.ContinentDao
import com.dawmlab.tema1.data.models.Animal
import com.dawmlab.tema1.data.models.AnimalContinentCrossRef
import com.dawmlab.tema1.data.models.Continent
import com.dawmlab.tema1.model.Model
import kotlinx.coroutines.launch
import java.util.Locale


class Fragment_1: Fragment(), OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var items: MutableList<Model>
    private lateinit var animalDao: AnimalDao
    private lateinit var continentDao: ContinentDao
    private lateinit var animalContinentDao: AnimalContinentDao
    private lateinit var animalContinentDatabase: AnimalContinentDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        items = mutableListOf()
        animalContinentDatabase=AnimalContinentDatabase.getInstance(requireContext())
        animalDao=animalContinentDatabase.animalDao()
        continentDao=animalContinentDatabase.continentDao()
        animalContinentDao=animalContinentDatabase.animalContinentDao()
        //fill the continent table with the continents if they are not already there
        lifecycleScope.launch{

            if(continentDao.getContinentByName("Europa")==null){
                continentDao.insertContinent(Continent("Europa"))
            }
            if(continentDao.getContinentByName("Africa")==null){
                continentDao.insertContinent(Continent("Africa"))
            }
            if(continentDao.getContinentByName("Americi")==null){
                continentDao.insertContinent(Continent("Americi"))
            }
            if(continentDao.getContinentByName("Asia")==null){
                continentDao.insertContinent(Continent("Asia"))
            }
            if(continentDao.getContinentByName("Australia")==null){
                continentDao.insertContinent(Continent("Australia"))
            }
        }


        //from the database get the list with the animals and continents and add them to the items list
        lifecycleScope.launch{
            val animalContinentList=animalContinentDao.getAllAnimalContinentCrossRef()
            for (animalContinent in animalContinentList){
                val animal=animalDao.getAnimalNameById(animalContinent.animalId)
                val continent=continentDao.getContinentById(animalContinent.continentId)
                items.add(Model(Model.Continents.valueOf(continent.name),animal))
            }

        }


    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_1, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //find add button and set on click listener
        val addButton= requireActivity().findViewById<Button>(R.id.addButton)

        addButton.setOnClickListener {
            //get the name and continent from the edit text fields
            val animalName= requireActivity().findViewById<EditText>(R.id.animalTextField).text.toString()
            val continentName= requireActivity().findViewById<EditText>(R.id.continentTextField).text.toString()
            addAnimal(animalName, continentName)

        }

        val adapter = MultiViewTypeAdapter(items)
        adapter.setOnClickListener(this)
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
    }

    fun addAnimal(animal: String, continent: String) {


        val continent = continent.toLowerCase(Locale.ROOT).capitalize(Locale.ROOT)

        lifecycleScope.launch {

            //check if the continent is a valid one (case insensitive)
            //convert the continent to first letter uppercase rest lowercase

            Log.e("HomeActivity", continent)
            if (continentDao.getContinentByName(continent) == null) {

                Toast.makeText(
                    requireContext(),
                    "Continentul nu este valid",
                    Toast.LENGTH_SHORT
                ).show()
                return@launch
            }


            //if the animal is already in the database then update the continent in the animalContinent table

            if (animalDao.getAnimalByName(animal) != null) {
                val animalId = animalDao.getAnimalByName(animal).id
                val continentId = continentDao.getContinentByName(continent).id
                animalContinentDao.updateAnimalContinentCrossRef(animalId, continentId)
            }
            //if the animal is not in the database then add it to the animal table and add the animal and continent to the animalContinent table
            else {
                animalDao.insertAnimal(com.dawmlab.tema1.data.models.Animal(animal))
                val animalId = animalDao.getAnimalByName(animal).id
                val continentId = continentDao.getContinentByName(continent).id
                animalContinentDao.insertAnimalContinentCrossRef(
                    AnimalContinentCrossRef(
                        animalId,
                        continentId
                    )
                )
            }

        }
    }
    fun deleteAnimal(position: Int) {
       //get the animal and continent from the position
        val animal=items[position].animalName
        val continent=items[position].continent.toString()
        lifecycleScope.launch{
            //delete the animal from the animal table
            //get the animal id from the animal table
            val animalId=animalDao.getAnimalByName(animal).id
            animalDao.deleteAnimal(Animal(animal,animalId))
            //delete the animal and continent from the animalContinent table
            animalContinentDao.deleteAnimalContinentCrossRef(animalId)
        }

    }
    override fun onClick(view: View?, position: Int) {
        if (view?.id == R.id.delete) {
            deleteAnimal(position)
            Log.e("HomeActivity", "button clicked at position $position")
        }
        else {
            //ToDo: navigate to Fragment_2 and pass data
            val bundle = Bundle()
            bundle.putString("animalName", items[position].animalName)
            bundle.putString("continentName", items[position].continent.toString())

            findNavController().navigate(R.id.action_fragment_1_to_fragment_2, bundle)
            Log.e("HomeActivity", "button clicked")
        }
        }





   /* private fun getDataList(): List<Model> {
        //ToDo: add more animals
        val europeAnimals = listOf("Lion", "Tiger", "Elephant", "Wolf", "Brown bear", "Moose", "Lynx", "Red fox", "Wild boar", "Badger")
        val africaAnimals = listOf("Kangaroo", "Koala", "Wallaby", "Giraffe", "Zebra", "Cheetah", "Hippopotamus", "Hyena", "Ostrich", "Meerkat")
        val americaAnimals = listOf("Panda", "Gorilla", "Monkey", "Grizzly bear", "Jaguar", "Bald eagle", "Raccoon", "Armadillo", "Sloth", "Capybara")
        val asiaAnimals = listOf("Snow leopard", "Yak", "Orangutan", "Komodo dragon", "Red panda", "Tiger", "Gibbon", "Pangolin", "Bactrian camel", "Snowy owl")
        val australiaAnimals = listOf("Kangaroo", "Koala", "Wallaby", "Platypus", "Tasmanian devil", "Wombat", "Emu", "Kookaburra", "Cassowary", "Quokka")


        val dataList = mutableListOf<Model>()
        europeAnimals.forEach { animal ->
            dataList.add(Model(Model.Continents.Europe, animal))
        }
        africaAnimals.forEach { animal ->
            dataList.add(Model(Model.Continents.Africa, animal))
        }
        americaAnimals.forEach { animal ->
            dataList.add(Model(Model.Continents.Americi, animal))
        }
        asiaAnimals.forEach { animal ->
            dataList.add(Model(Model.Continents.Asia, animal))
        }
        australiaAnimals.forEach { animal ->
            dataList.add(Model(Model.Continents.Australia, animal))
        }
        dataList.shuffle()
        return dataList
    }*/

}