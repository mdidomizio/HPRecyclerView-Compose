package com.example.hp_recyclerview_compose

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.hp_recyclerview_compose.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: HarryPotterViewModel by viewModels()
    private val adapter = HarryPotterAdapter(emptyList())
    private lateinit var uiHandler: UiHandler


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        uiHandler = UiHandler(
            context = this,
            binding = binding,
            lifecycleOwner = this,
            viewModel = viewModel
        )

        uiHandler.setupUi()
    }
}
