package com.example.hp_recyclerview_compose

import android.content.Context
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hp_recyclerview_compose.databinding.ActivityMainBinding

class UiHandler (
    private val context: Context,
    private val binding: ActivityMainBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: HarryPotterViewModel
) {
    private val adapter = HarryPotterAdapter(emptyList())

    fun setupUi() {
        setUpRecyclerView()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.harryPotterData.observe(lifecycleOwner) {items ->
            adapter.updateData(items)
        }
        viewModel.isLoading.observe(lifecycleOwner) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }

        viewModel.error.observe(lifecycleOwner) { errorMessage ->
            Toast.makeText(context,errorMessage,Toast.LENGTH_LONG).show()
        }
    }

    private fun setUpRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = this@UiHandler.adapter
        }
    }
}