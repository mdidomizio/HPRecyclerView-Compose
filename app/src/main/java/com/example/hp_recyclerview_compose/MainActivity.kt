package com.example.hp_recyclerview_compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hp_recyclerview_compose.databinding.ActivityMainBinding


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
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.character.observe(this) { it ->
            adapter.updateData(it)
        }
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }

        viewModel.error.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()

        }
    }

    private fun setRecyclerView() {
        binding.recyclerView .apply {
            layoutManager = GridLayoutManager(this@MainActivity,3)
            adapter = this@MainActivity.adapter
        }
    }
}
