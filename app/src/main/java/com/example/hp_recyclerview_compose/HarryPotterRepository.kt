package com.example.hp_recyclerview_compose

class HarryPotterRepository {

    private val harryPotterService = RetrofitInstance.harryPotterService

    suspend fun getHarryPotterCharacters(): List<HpCharacters> {
        return harryPotterService.getData()
    }
}
