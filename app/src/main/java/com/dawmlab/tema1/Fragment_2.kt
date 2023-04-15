package com.dawmlab.tema1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class Fragment_2: Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_2, container, false)
        val animalName = arguments?.getString("animalName")
        val continentName = arguments?.getString("continentName")
        val name = view.findViewById<TextView>(R.id.name)
        val continent = view.findViewById<TextView>(R.id.continent)
        name.text = animalName
        continent.text = continentName
        when (continentName) {
            "Europe" -> view.setBackgroundResource(R.color.Europa)
            "Africa" -> view.setBackgroundResource(R.color.Africa)
            "America" -> view.setBackgroundResource(R.color.Americi)
            "Asia" -> view.setBackgroundResource(R.color.Asia)
            "Australia" -> view.setBackgroundResource(R.color.Australia)
        }
        return view
    }
}