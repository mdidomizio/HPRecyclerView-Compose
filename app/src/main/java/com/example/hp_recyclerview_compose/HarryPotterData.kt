package com.example.hp_recyclerview_compose

sealed class HarryPotterData {
    data class StudentsHeader(val title: String) : HarryPotterData()
    data class StudentItem(val student: HpStudent) : HarryPotterData()
}

    data class HpStudent(
        val id: String,
        val image: String,
        val name: String,
        val house: String,
        val isHogwartsStudent: Boolean = true,
        val patronus: String
    )




/*data class HarryPotterData(
    val id: String,
    val image: String,
    val name: String,
    val actor: String,
    val isAlive: Boolean,
    val alternateActors: List<String>,
    val alternateNames: List<String>,
    val ancestry: String,
    val isHogwartsStaff: Boolean,
    val isHogwartsStudent: Boolean,
    val house: String,
    val patronus: String,
    val species: String,
    val wand: Wand,
    val isWizard: Boolean
)

data class Wand(
    val core: String,
    val length: Float,
    val wood: String
)*/



/*    data class HpStaff(
        val id: String,
        val image: String,
        val name: String,
        val isHogwartsStudent: Boolean
    ) : HarryPotterData()

    data class HpWizard(
        val id: String,
        val image: String,
        val name: String,
        val isWizard: Boolean
    ) : HarryPotterData()

    data class HpHouse(
        val id: String,
        val image: String,
        val name: String,
        val house: String
    ) : HarryPotterData()
}*/
