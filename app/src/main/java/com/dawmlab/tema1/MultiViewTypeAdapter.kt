package com.dawmlab.tema1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by anupamchugh on 09/02/16.
 */
class MultiViewTypeAdapter(private val items: List<Model>) :RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {

        return items[position].continent.toInt()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            Model.Continents.Europe.toInt() -> {
                val view = inflater.inflate(R.layout.item_europa, parent, false)
                EuropeViewHolder(view)
            }
            Model.Continents.Africa.toInt() -> {
                val view = inflater.inflate(R.layout.item_africa, parent, false)
                AfricaViewHolder(view)
            }
            Model.Continents.Asia.toInt() -> {
                val view = inflater.inflate(R.layout.item_asia, parent, false)
                AsiaViewHolder(view)
            }
            Model.Continents.Americi.toInt() -> {
                val view = inflater.inflate(R.layout.item_americi, parent, false)
                AmericiViewHolder(view)
            }
            Model.Continents.Australia.toInt() -> {
                val view = inflater.inflate(R.layout.item_australia, parent, false)
                AustraliaViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (holder) {
            is EuropeViewHolder -> holder.bind(item)
            is AfricaViewHolder -> holder.bind(item)
            is AsiaViewHolder -> holder.bind(item)
            is AmericiViewHolder -> holder.bind(item)
            is AustraliaViewHolder -> holder.bind(item)
            else -> throw IllegalArgumentException("Invalid view holder type")
        }
    }

    inner class EuropeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val continentTextView: TextView = itemView.findViewById(R.id.continent)
        private val animalTextView: TextView = itemView.findViewById(R.id.name)

        fun bind(item: Model) {
            continentTextView.text = item.continent.name
            animalTextView.text = item.animalName
        }
    }
    inner class AfricaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val continentTextView: TextView = itemView.findViewById(R.id.continent)
        private val animalTextView: TextView = itemView.findViewById(R.id.name)

        fun bind(item: Model) {
            continentTextView.text = item.continent.name
            animalTextView.text = item.animalName
        }
    }
    inner class AsiaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val continentTextView: TextView = itemView.findViewById(R.id.continent)
        private val animalTextView: TextView = itemView.findViewById(R.id.name)

        fun bind(item: Model) {
            continentTextView.text = item.continent.name
            animalTextView.text = item.animalName
        }
    }
    inner class AmericiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val continentTextView: TextView = itemView.findViewById(R.id.continent)
        private val animalTextView: TextView = itemView.findViewById(R.id.name)

        fun bind(item: Model) {
            continentTextView.text = item.continent.name
            animalTextView.text = item.animalName
        }
    }
    inner class AustraliaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val continentTextView: TextView = itemView.findViewById(R.id.continent)
        private val animalTextView: TextView = itemView.findViewById(R.id.name)

        fun bind(item: Model) {
            continentTextView.text = item.continent.name
            animalTextView.text = item.animalName
        }
    }
}

