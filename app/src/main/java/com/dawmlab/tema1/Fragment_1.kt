package com.dawmlab.tema1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Fragment_1: Fragment(), OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var items: List<Model>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        items = getDataList()

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

        val adapter = MultiViewTypeAdapter(items)
        adapter.setOnClickListener(this)
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
    }
    override fun onClick(view: View?, position: Int) {
        //ToDo: navigate to Fragment_2 and pass data
        val bundle = Bundle()
        bundle.putString("animalName", items[position].animalName)
        bundle.putString("continentName", items[position].continent.toString())

        findNavController().navigate(R.id.action_fragment_1_to_fragment_2,bundle)
        Log.e("HomeActivity", "button clicked")
    }
    private fun getDataList(): List<Model> {
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
    }

}