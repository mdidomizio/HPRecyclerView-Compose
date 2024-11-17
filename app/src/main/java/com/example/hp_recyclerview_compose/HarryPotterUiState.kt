package com.example.hp_recyclerview_compose

data class HarryPotterUiState(
    val data: List<HarryPotterData>,
    val isLoading: Boolean,
    val error: Error?
)
