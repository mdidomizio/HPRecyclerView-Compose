package com.example.hp_recyclerview_compose

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class HarryPotterViewModel(
    private val repository: HarryPotterRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<HarryPotterUiState>(HarryPotterUiState.Loading)
    val uiState: StateFlow<HarryPotterUiState> = _uiState.asStateFlow()


    init {
        fetchCharacters()
    }
    private fun fetchCharacters(){
        viewModelScope.launch {
            try {
                _uiState.value = HarryPotterUiState.Loading
                val characters = repository.getHarryPotterCharacters()
                // creates list with headers and student
                val items = mutableListOf<HarryPotterData>().apply {
                    add(HarryPotterData.StudentsHeader("Hogwarts' Students"))
                    addAll(characters.map {HarryPotterData.StudentItem(it)})
                }
                _uiState.update {
                    HarryPotterUiState.Success(items)
                }
            } catch (e: Exception) {
                _uiState.update {
                    HarryPotterUiState.Error(e.message ?: "Unknown error occurred")
                }
            }
        }
    }
}

