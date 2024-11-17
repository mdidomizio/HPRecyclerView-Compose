package com.example.hp_recyclerview_compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HarryPotterViewModelFactory (
    private val repository: HarryPotterRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HarryPotterViewModel::class.java)){
            return HarryPotterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}