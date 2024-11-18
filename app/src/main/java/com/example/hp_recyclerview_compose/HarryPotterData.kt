package com.example.hp_recyclerview_compose

data class HarryPotterData(
    val id: String,
    val image: String,
    val name: String,
    val actor: String,
    val isAlive: Boolean,
    val alternateActors: List<String>,
    val alternateNames: List<String>,
    val ancestry: String,
    val dateOfBirth: String,
    val eyeColour: String,
    val gender: String,
    val hairColour: String,
    val isHogwartsStaff: Boolean,
    val isHogwartsStudent: Boolean,
    val house: String,
    val patronus: String,
    val species: String,
    val wand: Wand,
    val isWizard: Boolean,
    val yearOfBirth: String
)

data class Wand(
    val core: String,
    val length: Float,
    val wood: String
)

/*interface HouseBelonging {
    enum class House(value: String) {
        Slytherin("Slytherin"),
        Gryffindor("Gryffindor"),
        Ravenclaw("Ravenclaw"),
        Hufflepuff("Hufflepuff")
    }

    fun getHouseBelonging(): String
}*/


