package com.dawmlab.tema1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class MultiViewTypeAdapter(private val items: List<Model>) :RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var clickListener: OnItemClickListener? = null


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

    fun setOnClickListener(itemClickListener: OnItemClickListener?) {
        clickListener = itemClickListener
    }
    inner class EuropeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val continentTextView: TextView = itemView.findViewById(R.id.continent)
        private val animalTextView: TextView = itemView.findViewById(R.id.name)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.delete)
        init {
            itemView.setOnClickListener(this)
            deleteButton.setOnClickListener(this)
        }

        fun bind(item: Model) {
            continentTextView.text = item.continent.name
            animalTextView.text = item.animalName
        }


        override fun onClick(view: View) {

            clickListener?.onClick(view, adapterPosition)

        }
    }
    inner class AfricaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val continentTextView: TextView = itemView.findViewById(R.id.continent)
        private val animalTextView: TextView = itemView.findViewById(R.id.name)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.delete)

        init {
            itemView.setOnClickListener(this)
            deleteButton.setOnClickListener(this)
        }
        fun bind(item: Model) {
            continentTextView.text = item.continent.name
            animalTextView.text = item.animalName
        }
        override fun onClick(view: View) {
            clickListener?.onClick(view, adapterPosition)
        }
    }
    inner class AsiaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        private val continentTextView: TextView = itemView.findViewById(R.id.continent)
        private val animalTextView: TextView = itemView.findViewById(R.id.name)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.delete)

        init {
            itemView.setOnClickListener(this)
            deleteButton.setOnClickListener(this)
        }
        fun bind(item: Model) {
            continentTextView.text = item.continent.name
            animalTextView.text = item.animalName
        }
        override fun onClick(view: View) {
            clickListener?.onClick(view, adapterPosition)
        }
    }
    inner class AmericiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        private val continentTextView: TextView = itemView.findViewById(R.id.continent)
        private val animalTextView: TextView = itemView.findViewById(R.id.name)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.delete)

        init {
            itemView.setOnClickListener(this)
            deleteButton.setOnClickListener(this)
        }
        fun bind(item: Model) {
            continentTextView.text = item.continent.name
            animalTextView.text = item.animalName
        }
        override fun onClick(view: View) {
            clickListener?.onClick(view, adapterPosition)
        }
    }
    inner class AustraliaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        private val continentTextView: TextView = itemView.findViewById(R.id.continent)
        private val animalTextView: TextView = itemView.findViewById(R.id.name)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.delete)

        init {
            itemView.setOnClickListener(this)
            deleteButton.setOnClickListener(this)
        }
        fun bind(item: Model) {
            continentTextView.text = item.continent.name
            animalTextView.text = item.animalName
        }
        override fun onClick(view: View) {
            clickListener?.onClick(view, adapterPosition)
        }
    }
}

