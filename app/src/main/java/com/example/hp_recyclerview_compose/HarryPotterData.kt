package com.example.hp_recyclerview_compose

import android.net.Uri

data class HarryPotterData(
    val actor: String,
    val alive: Boolean,
    val alternateActors: List<String>,
    val alternateNames: List<String>,
    val ancestry: String,
    val dateOfBirth: String,
    val eyeColour: String,
    val gender: String,
    val hairColour: String,
    val hogwartsStaff: Boolean,
    val hogwartsStudent: Boolean,
    val house: String,
    val id: String,
    val image: Uri,
    val name: String,
    val patronus: String,
    val species: String,
    val wand: Wand,
    val wizard: Boolean,
    val yearOfBirth: String
)

data class Wand(
    val core: String,
    val length: Float,
    val wood: String
)//(val image: Int, val text: String)
