package com.dawmlab.tema1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.dawmlab.tema1.R

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
            "Europa" -> view.setBackgroundResource(R.drawable.gradient_europa)
            "Africa" -> view.setBackgroundResource(R.drawable.gradient_africa)
            "Americi" -> view.setBackgroundResource(R.drawable.gradient_americi)
            "Asia" -> view.setBackgroundResource(R.drawable.gradient_asia)
            "Australia" -> view.setBackgroundResource(R.drawable.gradient_australia)
        }
        return view
    }
}