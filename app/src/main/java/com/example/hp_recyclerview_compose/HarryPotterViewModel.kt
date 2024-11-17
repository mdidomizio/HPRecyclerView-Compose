package com.example.hp_recyclerview_compose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HarryPotterViewModel : ViewModel() {
    private val repository = HarryPotterRepository()
    private val _character = MutableLiveData<List<HarryPotterData>>()
    val character: LiveData<List<HarryPotterData>> = _character
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        fetchApi()
    }
    private fun fetchApi(){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val data = repository.getHarryPotterData()
                _character.value = data
                _isLoading.value = false
            } catch (e: Exception) {
                _error.value = e.message ?: "An Error Occurred"
                _isLoading.value = false
            }
        }
    }
}
