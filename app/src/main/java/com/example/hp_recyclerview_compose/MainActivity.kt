package com.example.hp_recyclerview_compose

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hp_recyclerview_compose.databinding.ActivityMainBinding
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = HarryPotterAdapter(mutableListOf())
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
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    binding.progressBar.isVisible = state.isLoading
                    when {
                        state.error != null -> {
                            binding.recyclerView.isVisible = false
                        }
                        state.data.isNotEmpty() -> {
                            binding.recyclerView.isVisible = true
                            adapter.updateData(state.data)
                        }
                        else -> {
                            binding.recyclerView.isVisible = false
                        }
                    }
                }

            }
        }
    }

    private fun setRecyclerView() {
        binding.recyclerView .apply {
            layoutManager = GridLayoutManager(this@MainActivity,3)
            adapter = this@MainActivity.adapter
        }
    }
}
