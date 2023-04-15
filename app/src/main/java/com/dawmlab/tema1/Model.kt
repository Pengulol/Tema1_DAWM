package com.dawmlab.tema1


class Model(var continent: Continents, var animalName: String) {
    enum class Continents {
        Europe, Africa, Asia, Americi, Australia;

        fun toInt(): Int {
            return when (this) {
                Europe -> 0
                Africa -> 1
                Asia -> 2
                Americi -> 3
                Australia -> 4
            }
        }

    }
}

