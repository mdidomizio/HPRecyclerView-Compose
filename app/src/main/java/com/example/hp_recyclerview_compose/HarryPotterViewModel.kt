package com.example.hp_recyclerview_compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class HarryPotterViewModel(
    private val repository: HarryPotterRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HarryPotterUiState())
    val uiState: StateFlow<HarryPotterUiState> = _uiState.asStateFlow()


    init {
        loadItems()
    }
    private fun loadItems(){
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true, error = null) }
                val items = repository.getHarryPotterData()
                _uiState.update { it.copy(isLoading = false, data = items) }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = when (e) {
                            is UnknownHostException -> "No Internet connection"
                            is SocketTimeoutException -> "Connection timed out"
                            else -> "Failed to load items"

                        }
                    )
                }
            }
        }
    }
}
