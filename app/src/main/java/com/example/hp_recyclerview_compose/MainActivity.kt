package com.example.hp_recyclerview_compose

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hp_recyclerview_compose.databinding.ActivityMainBinding
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: HarryPotterAdapter
    private val repository: HarryPotterRepository by lazy {
        HarryPotterRepository()
    }
    private val viewModel: HarryPotterViewModel by viewModels {
        HarryPotterViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecyclerView()
        observerUiState()
    }

    private fun observerUiState () {
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    is HarryPotterUiState.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is HarryPotterUiState.Success -> {
                        binding.progressBar.isVisible = false
                        binding.recyclerView.isVisible = true
                        adapter.updateItems(state.items)
                    }
                    is HarryPotterUiState.Error -> {
                        binding.recyclerView.isVisible = false
                        println("Error: ${ state.message }")
                    }

                    }
                }
        }
    }

    private fun setRecyclerView() {
        binding.recyclerView .apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = HarryPotterAdapter { character ->
                println("Clicked character: ${character.name}")
            }
            adapter = this@MainActivity.adapter
        }
    }
}
