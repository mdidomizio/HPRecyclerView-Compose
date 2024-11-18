package com.example.hp_recyclerview_compose

data class HarryPotterUiState(
    val data: List<HarryPotterData> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
