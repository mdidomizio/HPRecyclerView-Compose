package com.example.hp_recyclerview_compose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HarryPotterViewModel: ViewModel(
   // private val dispatcher: CoroutineDispatcher = Dispatcher.IO
) {
    private val repository = HarryPotterRepository()
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
   // private val _harryPotterData = MutableLiveData<List<HarryPotterData>>()
    private val _harryPotterData = MutableStateFlow<Resource<List<HarryPotterData>>>(Resource.Loading())
    val harryPotterData: LiveData<List<HarryPotterData>> = _harryPotterData

    init {
        fetchHarryPotterApi()
    }


    fun fetchHarryPotterApi(){
        viewModelScope.launch (dispatcher){
            try {
                _harryPotterData.value = Resource.Loading()
                val data = repository.getHarryPotterData()
               // _harryPotterData.value = data

                var adapter = HarryPotterAdapter(data)
                binding.recyclerview

            } catch (e: Exception){
                e.printStackTrace()
            }
        }

    }
}

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val message: String) : Resource<T>()
    class Loading<T> : Resource<T>()
}
