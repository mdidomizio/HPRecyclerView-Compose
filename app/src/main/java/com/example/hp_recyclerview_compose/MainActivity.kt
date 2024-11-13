package com.example.hp_recyclerview_compose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hp_recyclerview_compose.databinding.ActivityMainBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: HarryPotterViewModel
    private lateinit var binding: ActivityMainBinding
    private val data = ArrayList<HarryPotterData>()
    private val adapter = HarryPotterAdapter(data)

    private val BASE_URL = "https://hp-api.onrender.com/api/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
        setUpViewModel()
     //   observeData()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview) //TODO check this part



        /*for (i in 1..150) {
            data.add(HarryPotterData(R.drawable.ic_baseline_folder_shared_24, "Item $i"))*/



            recyclerView.adapter = adapter
       // }
    }

    /*private fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.harryPotterData.collect { result ->

                    when(result) {
                        is Resource.Success -> {
                         // binding.progressBar.isVisible = false
                            harryPotterAdapter.submitlist(result.data)
                            binding.recyclerview.isVisible = true
                        }
                        is Resource.Error ->{
                            binding.recyclerview.isVisible = false
                            binding.tvError.isVisible = true
                            binding.tvError.text = result.message
                        }
                        is Resource.Loading ->{
                           // binding.progressBar.isVisible = true
                            binding.recyclerview.isVisible = false
                           // binding.tvError.isVisible = false
                        }
                    }

                }
            }
        }
    }*/

    private fun setUpViewModel() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //val harryPotterService = retrofit.create(HarryPotterService::class.java)
        //val repository = HarryPotterRepository()

       // initialise viewModel
        viewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return HarryPotterViewModel() as T
                }
            }
        ) [HarryPotterViewModel::class.java]
    }

    private fun setUpRecyclerView() {
        binding.recyclerview.apply {
            adapter = adapter
            layoutManager = GridLayoutManager(this@MainActivity, 3)
        }
    }
}