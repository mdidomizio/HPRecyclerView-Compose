package com.example.hp_recyclerview_compose

sealed class HarryPotterUiState{
    data object Loading : HarryPotterUiState()
    data class Success(val items: List<ListItem>) : HarryPotterUiState()
    data class Error(val message: String) : HarryPotterUiState()
}
