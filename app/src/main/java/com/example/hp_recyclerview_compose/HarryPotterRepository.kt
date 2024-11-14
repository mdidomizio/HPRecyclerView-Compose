package com.example.hp_recyclerview_compose

class HarryPotterRepository {

    private val harryPotterService = RetrofitInstance.harryPotterService

    suspend fun getHarryPotterData(): List<HarryPotterData> {
        return harryPotterService.getData()
    }
}
