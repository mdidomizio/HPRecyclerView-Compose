package com.example.hp_recyclerview_compose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HarryPotterViewModel(
    private val repository: HarryPotterRepository
) : ViewModel() {
   /* private val _character = MutableLiveData<List<HarryPotterData>>()
    val character: LiveData<List<HarryPotterData>> = _character*/
    private val _uiState = MutableStateFlow(HarryPotterUiState(
        data = TODO(),
        isLoading = TODO(),
        error = TODO()
    )
    )
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
                        error = e.message ?: "An Error Occurred"
                    )
                }
            }
        }
    }
}
