package com.example.hp_recyclerview_compose

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hp_recyclerview_compose.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: HarryPotterViewModel by viewModels()
    private val adapter = HarryPotterAdapter(emptyList())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecyclerView()

       /* val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 3)*/

        /*lifecycleScope.launch {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val dataService: HarryPotterService by lazy {
                retrofit.create(HarryPotterService::class.java)
            }
            val data = dataService.getData()
            recyclerView.adapter = HarryPotterAdapter(data)
        }*/
    }

    private fun setRecyclerView() {
        binding.recyclerview.apply {
            layoutManager = GridLayoutManager(this@MainActivity,3)
            adapter = this@MainActivity.adapter
        }
    }
}
