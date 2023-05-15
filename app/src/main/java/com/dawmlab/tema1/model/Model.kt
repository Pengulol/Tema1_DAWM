package com.dawmlab.tema1.model


class Model(var continent: Continents, var animalName: String) {
    enum class Continents {
        Europa, Africa, Asia, Americi, Australia;

        fun toInt(): Int {
            return when (this) {
                Europa -> 0
                Africa -> 1
                Asia -> 2
                Americi -> 3
                Australia -> 4
            }
        }

    }
}

