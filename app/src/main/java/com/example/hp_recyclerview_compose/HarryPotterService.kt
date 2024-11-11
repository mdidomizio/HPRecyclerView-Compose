package com.example.hp_recyclerview_compose

import retrofit2.http.GET

interface HarryPotterService {
    @GET("characters")
    suspend fun getData(): List<HarryPotterData>
}