package com.example.hp_recyclerview_compose

class HarryPotterRepository {
    private val harryPotterService = RetrofitInstance.harryPotterService

    suspend fun getHarryPotterData(): List<HarryPotterData> {
        return harryPotterService.getData()
    }
}

/*class HarryPotterRepository {
    private val harryPotterService = RetrofitInstance.harryPotterService

    suspend fun getHarryPotterData(): Resource<List<HarryPotterData>> {
        return try{
            val response = harryPotterService.getData()
            if (response.isSuccessful) {
                Resource.Success(response.body() ?; emptyList())
            } else{
                Resource.Error("Error ${response.code()}")
            }
            catch (e: Exception) {
                Resource.Error(e.message ?: "An error occurred")
            }
        }
    }*/
