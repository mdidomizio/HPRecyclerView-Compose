package com.example.hp_recyclerview_compose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HarryPotterViewModel: ViewModel() {

    init {
        viewModelScope.launch {  }
    }

    private val repository = HarryPotterRepository()
    private val _harryPotterData = MutableLiveData<List<HarryPotterData>>()
    val harryPotterData: LiveData<List<HarryPotterData>> = _harryPotterData

    fun fetchHarryPotterApi(){
        viewModelScope.launch {
            try {
                val data = repository.getHarryPotterData()
                _harryPotterData.value = data

                var adapter = HarryPotterAdapter(data)
                binding.recyclerview

            } catch (e: Exception){
                e.printStackTrace()
            }
        }

    }
}
