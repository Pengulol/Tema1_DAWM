package com.dawmlab.tema1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Fragment_1: Fragment() {

    private lateinit var recyclerView: RecyclerView

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

        val items = getDataList()

        val adapter = MultiViewTypeAdapter(items)
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
    }

    private fun getDataList(): List<Model> {
        //ToDo: add more animals
        val europeAnimals = listOf("Lion", "Tiger", "Elephant")
        val africaAnimals = listOf("Kangaroo", "Koala", "Wallaby")
        val americaAnimals = listOf("Panda", "Gorilla", "Monkey")
        val asiaAnimals = listOf("Lemur", "Zebra", "Giraffe")
        val australiaAnimals = listOf("Kangaroo", "Koala", "Wallaby")

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
        return dataList
    }

}